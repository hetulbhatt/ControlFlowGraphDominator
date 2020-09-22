# ControlFlowGraphDominator
The Dominator program produces dominator sets for all nodes in a connected digraph The dominator of the start node is the start node itself. The set of dominators for any other node n is the intersection of the set of dominators for all predecessors p of n. The node n is also in the set of dominators for n. private Map&lt;Character, Set&lt;Character>> preds contains immediate predecessors of all the nodes. Just in the case of root node, it is also an immediate predecessor of itself.

input1: https://en.wikipedia.org/wiki/Dominator_(graph_theory)

input2: https://www.cs.princeton.edu/courses/archive/fall03/cs528/handouts/a%20fast%20algorithm%20for%20finding.pdf
