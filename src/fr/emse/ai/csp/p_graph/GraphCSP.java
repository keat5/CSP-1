package fr.emse.ai.csp.p_graph;


import fr.emse.ai.csp.core.*;
import fr.emse.ai.csp.p_binary.EqualValuesNumberInColumnConstraint;
import fr.emse.ai.csp.p_binary.EqualValuesNumberInRowConstraint;
import fr.emse.ai.csp.p_binary.ThreeInLineConstraint;
import fr.emse.ai.csp.p_binary.UniqeLineConstraint;

import java.util.Arrays;
import java.util.List;

/**
 * Artificial Intelligence A Modern Approach (3rd Ed.): Figure 6.1, Page 204.<br>
 * <br>
 * The principal states and territories of Australia. Coloring this map can be
 * viewed as a constraint satisfaction problem (CSP). The goal is to assign
 * colors to each region so that no neighboring regions have the same color.
 *
 * @author Ruediger Lunde
 * @author Mike Stampone
 */
public class GraphCSP extends CSP {
    int graphSize = 10;
    Variable[][] graph = new Variable[graphSize][graphSize];

    public GraphCSP() {
        inicialize(graph);
        int[] colors = new int[graphSize%2 == 0 ? 2*graphSize : 2*graphSize + 1];
        for ( int i = 0; i< colors.length; i++ ){
            colors[i] = i+1;
        }
        Domain domain = new Domain(colors);

        for (Variable var : getVariables())
            setDomain(var, domain);

        for ( int i = 0; i<graphSize; i++) {
            for (int k = 0; k<graphSize; k++ ) {
                if(k+1<graphSize) addConstraint(new NotEqualConstraint(graph[i][k], graph[i][k+1]));
                if(i+1<graphSize) addConstraint(new NotEqualConstraint(graph[i][k], graph[i+1][k]));
            }
        }

        for ( int i = 0; i<graphSize; i++) {
            for (int k = 0; k < graphSize; k++) {
                addConstraint(new HarmonicConstraint(graph, i, k));
            }
        }

    }

    public void inicialize(Variable[][] tabVariable) {
        int size = tabVariable.length;
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                Variable var = new Variable(i + "-" + k);
                tabVariable[i][k] = var;
                addVariable(var);
            }

        }
        System.out.println(Arrays.deepToString(tabVariable));

    }

}