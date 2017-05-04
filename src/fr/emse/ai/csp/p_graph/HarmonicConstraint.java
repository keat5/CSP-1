package fr.emse.ai.csp.p_graph;

import fr.emse.ai.csp.core.Assignment;
import fr.emse.ai.csp.core.Constraint;
import fr.emse.ai.csp.core.Variable;
import fr.emse.ai.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DSERWATA on 04.05.2017.
 */
public class HarmonicConstraint implements Constraint {

    private List<Variable> scope;
    private static Variable[][] variables;
    private int x;
    private int y;

    public HarmonicConstraint(Variable[][] vars, int x, int y) {
        variables = vars;
        scope = new ArrayList<Variable>();
        this.x = x;
        this.y = y;
        scope.add(variables[x][y]);
    }

    @Override
    public List<Variable> getScope() {
        return scope;
    }

    @Override
    public boolean isSatisfiedWith(Assignment assignment) {
        List<Pair<Variable,Variable>> edges = new ArrayList<>();
        for ( int i = 0; i<variables.length;i++ ){
            for ( int k = 0; k<variables.length;k++ ){
                /*while ( assignment.getAssignment(variables[i][k]) != null ){

                }*/
                if (i!=x && k!=y && assignment.getAssignment(variables[i][k]) != null){
                    if(k+1<variables.length && assignment.hasAssignmentFor(variables[i][k+1])) edges.add(new Pair<>(variables[i][k], variables[i][k+1]));
                    if(i+1<variables.length && assignment.hasAssignmentFor(variables[i+1][k])) edges.add(new Pair<>(variables[i][k], variables[i+1][k]));
                }
            }
        }
        List<Pair<Variable,Variable>> newEdges = new ArrayList<>();
        for (int i = x-1; i<=x+1; i+=2){
            if(i>=0 && i<variables.length && assignment.hasAssignmentFor(variables[i][y])){
                newEdges.add(new Pair<>(variables[x][y], variables[i][y]));
            }
        }
        for (int k = y-1; k<=y+1; k+=2){
            if( k>=0 && k<variables.length
                    && assignment.hasAssignmentFor(variables[x][k])){
                newEdges.add(new Pair<>(variables[x][y], variables[x][k]));
            }
        }

        if (edges.size()>0){
            for (Pair<Variable,Variable> edge: edges){
                for (Pair<Variable,Variable> newEdge: newEdges){
                    if((assignment.getAssignment(edge.getFirst()).equals(assignment.getAssignment(newEdge.getFirst()))
                            && assignment.getAssignment(edge.getSecond()).equals(assignment.getAssignment(newEdge.getSecond())))
                        || (assignment.getAssignment(edge.getFirst()).equals(assignment.getAssignment(newEdge.getSecond()))
                            && assignment.getAssignment(edge.getFirst()).equals(assignment.getAssignment(newEdge.getSecond())))
                            ){
                        return false;
                    }

                }
            }
        }
        return true;
    }
}
