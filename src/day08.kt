private fun traverse(tree: TreeNode, f: (List<Int>) -> Int): Int {
    return when (tree) {
        is TreeNode.Leaf -> f(tree.metadata)
        is TreeNode.Node -> f(tree.children.map { traverse(it, f) }) + f(tree.metadata)
    }
}

sealed class TreeNode {
    data class Leaf(val metadata: List<Int>): TreeNode()
    data class Node(val children: List<TreeNode>, val metadata: List<Int>): TreeNode()
}

internal fun sumMetadata(tree: TreeNode) = traverse(tree) { metadata: List<Int> -> metadata.sum() }

internal fun rootNodeValue(tree: TreeNode): Int {
    return when (tree) {
        is TreeNode.Leaf -> tree.metadata.sum()
        is TreeNode.Node -> tree.metadata
                    .filter { it != 0 }
                    .filter { it <= tree.children.size }
                    .map { it -> tree.children[it - 1] }
                    .map { rootNodeValue(it) }
                    .sum()
    }
}
