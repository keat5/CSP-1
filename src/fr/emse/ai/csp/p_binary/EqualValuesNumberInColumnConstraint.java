package fr.emse.ai.csp.p_binary;

import fr.emse.ai.csp.core.Assignment;
import fr.emse.ai.csp.core.Constraint;
import fr.emse.ai.csp.core.Variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by DSERWATA on 24.04.2017.
 */
public class EqualValuesNumberInColumnConstraint implements Constraint {

    private Variable[] variables;
    private List<Variable> scope;

    public EqualValuesNumberInColumnConstraint(Variable[][] vars, int column) {

        variables=new Variable[vars.length];
        for(int i = 0; i<vars.length; i++) {
            this.variables[i] = vars[i][column];
        }
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
        return Arrays.asList(variables).stream().filter(e -> ( assignment.getAssignment(e) != null) && (int)assignment.getAssignment(e)==1).count() <= variables.length / 2
                && Arrays.asList(variables).stream().filter( e -> ( assignment.getAssignment(e) != null) && (int)assignment.getAssignment(e)==0).count() <= variables.length / 2;
    }
}