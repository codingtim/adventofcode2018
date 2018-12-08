import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.assertEquals

internal class Day08Test {

    @Test
    internal fun metadataSumTest() {
        val tree = TreeNode.Node(
                listOf(
                        TreeNode.Leaf(listOf(10, 11, 12)),
                        TreeNode.Node(
                                listOf<TreeNode>(TreeNode.Leaf(listOf(99))),
                                listOf(2)
                        )
                ),
                listOf(1, 1, 2)
        )
        val result = sumMetadata(tree)
        assertEquals(138, result)
    }

    @Test
    internal fun parseTest() {
        val tree = TreeNode.Node(
                listOf(
                        TreeNode.Leaf(listOf(10, 11, 12)),
                        TreeNode.Node(
                                listOf<TreeNode>(TreeNode.Leaf(listOf(99))),
                                listOf(2)
                        )
                ),
                listOf(1, 1, 2)
        )
        assertEquals(tree, parse("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2"))
    }

    @Test
    internal fun puzzle() {
        val input = Files.readAllLines(Paths.get("test", "day08"))[0]
        println(sumMetadata(parse(input)))
    }

    private fun parse(input: String): TreeNode {
        val iterator = input.split(" ").map { s -> Integer.valueOf(s) }.iterator()
        return parse(iterator)
    }

    private fun parse(input: Iterator<Int>): TreeNode {
        val numberOfChildren = input.next()
        val metadataCount = input.next()
        return if(numberOfChildren > 0) {
            TreeNode.Node(toChildren(numberOfChildren, input), toMetadata(metadataCount, input))
        } else {
            TreeNode.Leaf(toMetadata(metadataCount, input))
        }
    }

    private fun toMetadata(metadataCount: Int, input: Iterator<Int>): List<Int> {
        return toMetadata(metadataCount, input, listOf())
    }

    private fun toChildren(numberOfChildren: Int, input: Iterator<Int>): List<TreeNode> {
        return toChildren(numberOfChildren, input, listOf())
    }

    private tailrec fun toMetadata(metadataCount: Int, input: Iterator<Int>, acc: List<Int>): List<Int> {
        return if(metadataCount == 0) {
            acc
        } else{
            toMetadata(metadataCount - 1, input, acc + input.next())
        }
    }

    private fun toChildren(numberOfChildren: Int, input: Iterator<Int>, acc: List<TreeNode>): List<TreeNode> {
        return if (numberOfChildren == 0) {
            acc
        } else {
            toChildren(numberOfChildren - 1, input, acc + parse(input))
        }
    }

}