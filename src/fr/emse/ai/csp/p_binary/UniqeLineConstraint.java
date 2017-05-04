package fr.emse.ai.csp.p_binary;

import fr.emse.ai.csp.core.Assignment;
import fr.emse.ai.csp.core.Constraint;
import fr.emse.ai.csp.core.Variable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DSERWATA on 24.04.2017.
 */
public class UniqeLineConstraint implements Constraint {

    private List<Variable> scope;
    private static Variable[][] variables;
    private static Variable[][] variablesTrans;
    int x;
    int y;

    public UniqeLineConstraint ( Variable[][] vars, int x, int y ) {
        variables = vars;
        variablesTrans = new Variable[vars.length][vars.length];
        transVariable();
        this.x = x;
        this.y = y;
        scope = new ArrayList<>(vars.length*vars.length);
        scope.add(variables[x][y]);
        /*for (Variable[] vs: vars)
            for ( Variable v: vs ){
                scope.add(v);
            }*/
    }

    @Override
    public List<Variable> getScope() {
        return scope;
    }


    private Variable[] varRow;
    private Variable[] varColumn;
    @Override
    public boolean isSatisfiedWith(Assignment assignment) {
        if ( isFullLine(variables[x], assignment) ) {
            for (int i = 0; i < variables.length; i++) {
                if ( i!=x ) {
                    varRow = variables[i];
                    if (isFullLine(varRow, assignment)) {
                        if (areLinesEqual(varRow, variables[x], assignment))
                            return false;

                    }
                }
            }

            for (int i1 = 0, variablesTransLength = variablesTrans.length; i1 < variablesTransLength; i1++) {
                if (i1!=x || true) {
                    varRow = variablesTrans[i1];
                    if (isFullLine(varRow, assignment)) {
                        if (areLinesEqual(varRow, variables[x], assignment))
                            return false;
                    }
                }
            }
        }

        varColumn = new Variable[variables.length];
        for ( int i = 0; i<varColumn.length; i++ ) {
            varColumn[i] = variables[i][y];
        }

        if ( isFullLine(varColumn, assignment) ) {
                for (int i1 = 0, variablesLength = variables.length; i1 < variablesLength; i1++) {
                    if (i1!=y || true) {
                        varRow = variables[i1];
                        if (isFullLine(varRow, assignment)) {
                            if (areLinesEqual(varRow, varColumn, assignment))
                                return false;
                        }
                    }
                }

            for (int i = 0, variablesTransLength = variablesTrans.length; i < variablesTransLength; i++) {
                if (i!=y) {
                    varRow = variablesTrans[i];
                    if (isFullLine(varRow, assignment)) {
                        if (areLinesEqual(varRow, varColumn, assignment))
                            return false;
                    }
                }
            }


        }
        return true;
    }

    private boolean isFullLine(Variable[] vars, Assignment assignment) {
        for ( Variable v: vars )
            if ( assignment.getAssignment(v) == null )
                return false;
        return true;
    }

    private boolean areLinesEqual(Variable[] vars1, Variable[] vars2,Assignment assignment) {
        for (int j = 0; j < vars1.length; j++) {
            if (! (assignment.getAssignment(vars1[j]) == assignment.getAssignment(vars2[j])))
                return false;
        }
        return true;
    }

    private void transVariable(){
        for ( int i = 0; i<variables.length; i++ ) {
            for ( int j = 0; j<variables.length; j++ ){
                variablesTrans[i][j] = variables[j][i];
            }
        }
    }
}
