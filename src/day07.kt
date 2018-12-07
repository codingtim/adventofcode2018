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

private fun takeStep(step: Step, tail: List<Step>): List<Step> {
    return tail.map { it.stepTaken(step) }
            .sortedWith(StepComparator)
}

internal fun solveSteps(input: List<Step>): String {
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

internal fun solveStepsConcurrent(input: List<Step>, workers: Int, timeNeeded: (String) -> (Int)): Int {
    tailrec fun solveStepsConcurrent(input: List<Step>, working: List<Pair<Int, Step>>, result: Int): Int {
        return if (input.isEmpty() && working.isEmpty()) {
            result
        } else {
            return if (working.size < workers && !input.isEmpty() && input.head.requiredSteps.isEmpty()) {
                solveStepsConcurrent(input.tail, working + Pair(result + timeNeeded(input.head.name), input.head), result)
            } else {
                val stepsDone = working.filter { (finishedAt, _) -> finishedAt == result }.toList()
                val steps = stepsDone.fold(input) { acc, (_, step) -> takeStep(step, acc) }
                solveStepsConcurrent(
                        steps,
                        working.filter { (finishedAt, _) -> finishedAt > result },
                        result + 1
                )
            }
        }
    }
    return solveStepsConcurrent(input, listOf(), 0)
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