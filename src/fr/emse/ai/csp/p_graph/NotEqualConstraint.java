package fr.emse.ai.csp.p_graph;

import fr.emse.ai.csp.core.Assignment;
import fr.emse.ai.csp.core.Constraint;
import fr.emse.ai.csp.core.Variable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a p_binary constraint which forbids equal values.
 *
 * @author Ruediger Lunde
 */
public class NotEqualConstraint implements Constraint {

    private Variable var1;
    private Variable var2;
    private List<Variable> scope;

    public NotEqualConstraint(Variable var1, Variable var2) {
        this.var1 = var1;
        this.var2 = var2;
        scope = new ArrayList<Variable>(2);
        scope.add(var1);
        scope.add(var2);
    }

    @Override
    public List<Variable> getScope() {
        return scope;
    }

    @Override
    public boolean isSatisfiedWith(Assignment assignment) {
        Object value1 = assignment.getAssignment(var1);
        return value1 == null || !value1.equals(assignment.getAssignment(var2));
    }
}
