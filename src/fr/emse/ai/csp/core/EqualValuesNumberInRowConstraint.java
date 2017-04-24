package fr.emse.ai.csp.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by DSERWATA on 24.04.2017.
 */
public class EqualValuesNumberInRowConstraint implements Constraint {

    private Variable[] variables;
    private Variable var;
    private List<Variable> scope;

    public EqualValuesNumberInRowConstraint(Variable[][] vars, int row) {
        this.variables = vars[row];
        scope=new ArrayList<Variable>(vars.length);
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
        long zerosNumber = Arrays.asList(variables).stream().filter( e -> ( assignment.getAssignment(e) != null) && assignment.getAssignment(e).equals("0")).count();
        long onesNumber = Arrays.asList(variables).stream().filter( e -> ( assignment.getAssignment(e) != null) && assignment.getAssignment(e).equals("1")).count();
        return zerosNumber <= variables.length / 2
                && onesNumber <= variables.length / 2;
    }
}