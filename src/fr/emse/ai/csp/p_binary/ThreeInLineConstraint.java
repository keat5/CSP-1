package fr.emse.ai.csp.p_binary;

        import fr.emse.ai.csp.core.Assignment;
        import fr.emse.ai.csp.core.Constraint;
        import fr.emse.ai.csp.core.Variable;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Klak on 2017-04-24.
 */
public class ThreeInLineConstraint implements Constraint {
    private Variable var1;
    private Variable var2;
    private Variable var3;
    private List<Variable> scope;

    public ThreeInLineConstraint(Variable var1, Variable var2,Variable var3) {

        this.var1 = var1;
        this.var2 = var2;
        this.var3=var3;
        scope = new ArrayList<Variable>(3);
        scope.add(var1);
        scope.add(var2);
        scope.add(var3);
    }

    @Override
    public List<Variable> getScope() {
        return scope;
    }

    @Override
    public boolean isSatisfiedWith(Assignment assignment) {
        Object value1 = assignment.getAssignment(var1);
        Object value2 = assignment.getAssignment(var2);
        Object value3 = assignment.getAssignment(var3);


        return value1==null||value2==null||value3==null||!(value1 == value2 &&value2 == value3);
    }
}
