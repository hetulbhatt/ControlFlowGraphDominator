# ControlFlowGraphDominator
The CFG Dominator program produces dominator sets for all nodes in a connected digraph The dominator of the start node is the start node itself. The set of dominators for any other node n is the intersection of the set of dominators for all predecessors p of n. The node n is also in the set of dominators for n. ```Map<Character, Set<Character>> preds``` contains immediate predecessors of all the nodes. Just in the case of start node, it also contains itself as a predecessor.


Input text files contain CFGs from the following sites:

input1: https://en.wikipedia.org/wiki/Dominator_(graph_theory)

input2: https://www.cs.princeton.edu/courses/archive/fall03/cs528/handouts/a%20fast%20algorithm%20for%20finding.pdf

You can also provide a CFG as an input interactively during the program runtime.
