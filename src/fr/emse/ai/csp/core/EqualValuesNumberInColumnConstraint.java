package fr.emse.ai.csp.core;

import java.util.Arrays;
import java.util.List;

/**
 * Created by DSERWATA on 24.04.2017.
 */
public class EqualValuesNumberInColumnConstraint implements Constraint {

    private Variable[] variables;
    private Variable var;
    private List<Variable> scope;

    public EqualValuesNumberInColumnConstraint(Variable[][] vars, int column) {
        for(int i = 0; i<vars.length; i++) {
            this.variables[i] = vars[i][column];
        }
        for(int i = 0; i < variables.length; i++) {
            scope.add(variables[i]);
        }
    }

    @Override
    public List<Variable> getScope() {
        return scope;
    }

    @Override
    public boolean isSatisfiedWith(Assignment assignment) {
        return Arrays.asList(variables).stream().filter(e -> ( e != null) && assignment.getAssignment(e).equals("0")).count() < variables.length / 2
                && Arrays.asList(variables).stream().filter( e -> ( e != null) && assignment.getAssignment(e).equals("1")).count() < variables.length / 2;
    }
}
