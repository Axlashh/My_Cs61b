public class NBody {

    public static void main(String[] args){
        
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
            Planet tem = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
            ans[i] = tem;
        }
        return ans;
    }
}
