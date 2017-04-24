package fr.emse.ai.csp.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DSERWATA on 24.04.2017.
 */
public class UniqeLineConstraint implements Constraint {

    private List<Variable> scope;
    private Variable[][] variables;
    int x;
    int y;

    public UniqeLineConstraint ( Variable[][] vars, int x, int y ) {
        variables = vars;
        this.x = x;
        this.y = y;
        scope = new ArrayList<>(vars.length*vars.length);
        for (Variable[] vs: vars)
            for ( Variable v: vs ){
                scope.add(v);
            }
    }

    @Override
    public List<Variable> getScope() {
        return scope;
    }

    @Override
    public boolean isSatisfiedWith(Assignment assignment) {
        if ( isFullLine(variables[x], assignment) ) {
            for (int i = 0; i < variables.length; i++) {
                if ( i!=x ) {
                    Variable[] varRow = variables[i];
                    if (isFullLine(varRow, assignment)) {
                        if (areLinesEqual(varRow, variables[x], assignment))
                            return false;

                    }
                }
            }

            /*Variable[][] variablesTrans = new Variable[variables.length][variables.length];
            for ( int i = 0; i<variables.length; i++ ) {
                for ( int j = 0; j<variables.length; j++ ){
                    variablesTrans[i][j] = variables[j][i];
                }
            }

            for (int i1 = 0, variablesTransLength = variablesTrans.length; i1 < variablesTransLength; i1++) {
                if (i1!=x) {
                    Variable[] varRow = variablesTrans[i1];
                    if (isFullLine(varRow, assignment)) {
                        boolean isTheSame = true;
                        for (int i = 0; i < varRow.length; i++) {
                            if (!variables[x][i].equals(varRow[i]))
                                isTheSame = false;
                        }
                        return isTheSame;
                    }
                }
            }*/
        }

        Variable[] varColumn = new Variable[variables.length];
        for ( int i = 0; i<varColumn.length; i++ ) {
            varColumn[i] = variables[i][y];
        }
        if ( isFullLine(varColumn, assignment) ) {
                /*for (int i1 = 0, variablesLength = variables.length; i1 < variablesLength; i1++) {
                    if (i1!=y) {
                        Variable[] varRow2 = variables[i1];
                        if (isFullLine(varRow2, assignment)) {
                            boolean isTheSame = true;
                            for (int i = 0; i < varRow2.length; i++) {
                                if (!varColumn[i].equals(varRow2[i]))
                                    isTheSame = false;
                            }
                            return isTheSame;
                        }
                    }
                }*/

            Variable[][] variablesTrans = new Variable[variables.length][variables.length];
            for ( int i = 0; i<variables.length; i++ ) {
                for ( int j = 0; j<variables.length; j++ ){
                    variablesTrans[i][j] = variables[j][i];
                }
            }

            for (int i = 0, variablesTransLength = variablesTrans.length; i < variablesTransLength; i++) {
                if (i!=y) {
                    Variable[] varRow = variablesTrans[i];
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
            if (!assignment.getAssignment(vars1[j]).equals(assignment.getAssignment(vars2[j])))
                return false;
        }
        return true;
    }
}
