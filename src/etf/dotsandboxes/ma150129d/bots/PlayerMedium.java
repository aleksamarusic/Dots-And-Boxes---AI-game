package etf.dotsandboxes.ma150129d.bots;

import etf.dotsandboxes.ma150129d.structs.State;

public class PlayerMedium extends Player {
    private Tree tree;
    private int maxDepth;

    public PlayerMedium(State state, int maxDepth)
    {
        this.tree = new Tree(state);
        this.maxDepth = maxDepth;
        tree.formTree(maxDepth);
    }

    private int AlphaBeta(Tree.Node node, int maxDepth, int currDepth, int alpha, int beta)
    {
        if (node.getState().heuristicFunction() == 0) {
            if (currDepth % 2 == 0) {
                node.setValue(0);
            }
            else {
                node.setValue(1);
            }
            return node.getValue();
        }
        return 0; //placeholder
        /*if (currDepth == maxDepth) {
            if (currDepth % 2 == 0)
                return node.Value = 1;
            else
                return node.Value = 0;
        }
        int bestValue, currValue;
        if (currDepth % 2 == 0)
            bestValue = Int32.MinValue;
        else
            bestValue = Int32.MaxValue;
        for (int j = 0; j < node.Children.Count; j++) {
            currValue = AlphaBeta(node.Children[j], maxDepth, currDepth + 1, alpha, beta);
            if (currDepth % 2 == 0 && currValue > bestValue) {
                bestValue = currValue;
                if (bestValue >= beta) {
                    j = node.Children.Count;
                    return node.Value = bestValue;
                }
                alpha = Math.Max(alpha, bestValue);
            }
            if (currDepth % 2 == 1 && currValue < bestValue) {
                bestValue = currValue;
                if (bestValue <= alpha) {

                    return node.Value = bestValue;
                }
                beta = Math.Min(beta, bestValue);
            }
        }
        return node.Value = bestValue;*/
    }

    @Override
    public State playMove(State state) {
        this.tree = new Tree(state);
        tree.formTree(maxDepth);
        /*int val = AlphaBeta(tree.Root, maxDepth, 0, Int32.MinValue, Int32.MaxValue);
        int i = 0;
        while (i < tree.Root.Children.Count && val != tree.Root.Children[i].Value)
            i++;
        return i < tree.Root.Children.Count ? tree.Root.Children[i].State : null;*/
        return tree.getRoot().getState();
    }



}
