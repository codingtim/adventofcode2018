data class Step(val name: String, val requiredSteps: List<String>) {
    fun join(step: Step?): Step {
        return if(step == null) {
            this
        } else {
            return Step(name, requiredSteps + step.requiredSteps)
        }
    }

    fun stepTaken(step: Step) = Step(name, requiredSteps.filter { it != step.name })
}

internal fun solveSteps(input: List<Step>): String {
    fun takeStep(step: Step, tail: List<Step>): List<Step> {
        return tail.map { it.stepTaken(step) }
                .sortedWith(StepComparator)
    }
    tailrec fun solveSteps(input: List<Step>, result: String): String {
        return if(input.isEmpty()) {
            result
        } else {
            val step = input.head
            solveSteps(takeStep(step, input.tail), result + step.name)
        }
    }
    return solveSteps(input, "")
}

internal fun parseSteps(input: List<String>): List<Step> {
    return input.mapNotNull { Regex("Step (.) must be finished before step (.) can begin.").matchEntire(it) }
            .flatMap { listOf(
                    Step(it.destructured.component1(), listOf()),
                    Step(it.destructured.component2(), listOf(it.destructured.component1()))
            )}
            .fold(mutableMapOf<String, Step>()) {map, step ->
                map.compute(step.name) {_, s -> step.join(s)}
                map
            }
            .values
            .toList()
            .sortedWith(StepComparator)
}

private object StepComparator: Comparator<Step> {
    override fun compare(step1: Step, step2: Step): Int {
        val sizeDiff = step1.requiredSteps.size.compareTo(step2.requiredSteps.size)
        return if (sizeDiff == 0) step1.name.compareTo(step2.name) else sizeDiff
    }

}