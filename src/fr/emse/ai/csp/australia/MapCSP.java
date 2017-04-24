package fr.emse.ai.csp.australia;


import fr.emse.ai.csp.core.*;

import java.util.Arrays;

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
public class MapCSP extends CSP {
    int sizeBinary = 6;
    Variable[][] tab = new Variable[sizeBinary][sizeBinary];
    public static final Variable NSW = new Variable("NSW");
    public static final Variable NT = new Variable("NT");
    public static final Variable Q = new Variable("Q");
    public static final Variable SA = new Variable("SA");
    public static final Variable T = new Variable("T");
    public static final Variable V = new Variable("V");
    public static final Variable WA = new Variable("WA");
    public static final String RED = "RED";
    public static final String GREEN = "GREEN";
    public static final String BLUE = "BLUE";

    /**
     * Constructs a map CSP for the principal states and territories of
     * Australia, with the colors Red, Green, and Blue.
     */
    public MapCSP() {
        //collectVariables();
        inicialize(tab);
        Domain colors = new Domain(new String[]{"0", "1"});

        for (Variable var : getVariables())
            setDomain(var, colors);
        for (int i = 0; i < sizeBinary; i++) {
            for (int k = 0; k < sizeBinary - 2; k++) {
                addConstraint(new ThreeInLineConstraint(tab[i][k], tab[i][k + 1], tab[i][k + 2]));
            }
        }
        for (int i = 0; i < sizeBinary-2; i++) {
            for (int k = 0; k < sizeBinary; k++) {
                addConstraint(new ThreeInLineConstraint(tab[i][k], tab[i+1][k], tab[i+2][k]));
            }
        }
        for (int i = 0; i < sizeBinary; i++) {
            addConstraint(new EqualValuesNumberInColumnConstraint(tab, i));
            addConstraint(new EqualValuesNumberInRowConstraint(tab, i));
        }

        for ( int i = 0; i < sizeBinary; i++ ) {
            for ( int j = 0; j < sizeBinary; j++ ) {
                addConstraint(new UniqeLineConstraint(tab, i, j));
            }
        }
      /*  addConstraint(new NotEqualConstraint(WA, NT));
        addConstraint(new NotEqualConstraint(WA, SA));
        addConstraint(new NotEqualConstraint(NT, SA));
        addConstraint(new NotEqualConstraint(NT, Q));
        addConstraint(new NotEqualConstraint(SA, Q));
        addConstraint(new NotEqualConstraint(SA, NSW));
        addConstraint(new NotEqualConstraint(SA, V));
        addConstraint(new NotEqualConstraint(Q, NSW));
        addConstraint(new NotEqualConstraint(NSW, V));
        */


        /**
         * Returns the principle states and territories of Australia as a list of
         * variables.
         *
         * @return the principle states and territories of Australia as a list of
         * variables.
         */
    }
    private void collectVariables() {
        addVariable(NSW);
        addVariable(WA);
        addVariable(NT);
        addVariable(Q);
        addVariable(SA);
        addVariable(V);
        addVariable(T);

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