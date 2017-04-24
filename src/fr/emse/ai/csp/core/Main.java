package fr.emse.ai.csp.core;

import fr.emse.ai.csp.australia.MapCSP;

/**
 * Created by Klak on 2017-04-24.
 */
public class Main {
    public static void main(String[] args){

        MapCSP map = new MapCSP();
        BacktrackingStrategy bts = new BacktrackingStrategy();
      /*  bts.addCSPStateListener(new CSPStateListener() {
            @Override
            public void stateChanged(Assignment assignment, CSP csp) {
                System.out.println("Assignment evolved : " + assignment);
            }

            @Override
            public void stateChanged(CSP csp) {
                System.out.println("CSP evolved : " + csp);
            }
        });*/
        double start = System.currentTimeMillis();
        Assignment sol = bts.solve(map);
        double end = System.currentTimeMillis();
        System.out.println(sol);
        System.out.println("Time to solve = " + (end - start));
    }
}
