public class NBody {

    public static void main(String[] args){
        double t = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] pla = NBody.readPlanets(filename);
        double radius = NBody.readRadius(filename);
        StdDraw.setScale(-radius,radius);
        StdDraw.clear();
        for(Planet iter : pla){
            iter.draw();
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
        double xp = in.readDouble();
        double yp = in.readDouble();
        double xv = in.readDouble();
        double yv = in.readDouble();
        double ma = in.readDouble();
        String Filen = in.readString();
        for(int i=0;i<Numbers;i += 1){
            Planet tem = new Planet(xp,yp,xv,yv,ma,Filen);
            ans[i] = tem;
        }
        return ans;
    }
}
