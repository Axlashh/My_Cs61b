public class NBody {

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] pla = NBody.readPlanets(filename);
        double radius = NBody.readRadius(filename);
        StdDraw.setScale(-radius,radius);
        StdDraw.clear();
        int len = 0;
        for(Planet iter : pla){
            len += 1;
            //Ststem.out.println(len);
        }
        NBody.enableDoubleBuffering();
        for(int i=0;i<T;i += dt){
            double[] xForces = new double[len];
            double[] yForces = new double[len];
            for(int j=0;j<len;j += 1){
                xForces[j] = pla[j].calcNetForceExertedByX(pla);
                yForces[j] = pla[j].calcNetForceExertedByY(pla);
            }
            for(int j=0;j<len;j += 1){
                pla[j].update(dt, xForces[j], yForces[j]);
            }
            StdDraw.clear();
            for(int j=0;j<len;j += 1){
                pla[j].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", pla.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < pla.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  pla[i].xxPos, pla[i].yyPos, pla[i].xxVel,
                  pla[i].yyVel, pla[i].mass, pla[i].imgFileName);   
        }
}

    public static double readRadius(String args){
        In in = new In(args);
        int FirstInt = in.readInt();
        double SecondDouble = in.readDouble();
        return SecondDouble;
    }

    public static Planet[] readPlanets(String args){
        In in = new In(args);
        int Numbers = in.readInt();
        double radius = in.readDouble();
        Planet[] ans = new Planet[Numbers];
        for(int i=0;i<Numbers;i += 1){
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double ma = in.readDouble();
            String Filen = in.readString();
            Planet tem = new Planet(xp,yp,xv,yv,ma,Filen);
            ans[i] = tem;
        }
        return ans;
    }

    public static void enableDoubleBuffering(){
        StdDraw.enableDoubleBuffering();
    }
}
