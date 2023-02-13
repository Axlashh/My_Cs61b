public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static double G = 6.67e-11; 

    public Planet(double xP,double yP,double xV,
                 double yV,double m,String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public boolean equals(Planet p){
        if(this == p){
            return true;
        }
        return false;
    }

    public double calcDistance(Planet p){
        double ans = Math.pow(Math.pow(this.xxPos-p.xxPos,2)+Math.pow(this.yyPos-p.yyPos,2),0.5);
        return ans;
    }

    public double calcForceExertedBy(Planet p){
        double dis = this.calcDistance(p);
        double ans = G*this.mass*p.mass/(dis*dis);
        return ans;
    }

    public double calcForceExertedByX(Planet p){
        double force = this.calcForceExertedBy(p);
        double dis = this.calcDistance(p);
        double ans = force*(p.xxPos - xxPos)/dis;
        return ans;
    }

    public double calcForceExertedByY(Planet p){
        double force = this.calcForceExertedBy(p);
        double dis = this.calcDistance(p);
        double ans = force*(p.yyPos - yyPos)/dis;
        return ans;
    }

    public double calcNetForceExertedByX(Planet[] p){
        double ans = 0;
        for(Planet iter : p){
            if(this.equals(iter)) continue;
            ans += calcForceExertedByX(iter);
        }
        return ans;
    }

    public double calcNetForceExertedByY(Planet[] p){
        double ans = 0;
        for(Planet iter : p){
            if(this.equals(iter)) continue;
            ans += calcForceExertedByY(iter);
        }
        return ans;
    }

    public void update(double sec,double x,double y){
        double ax = x/mass;
        double ay = y/mass;
        xxVel += ax*sec;
        yyVel += ay*sec;
        xxPos += xxVel*sec;
        yyPos += yyVel*sec;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, imgFileName);
        StdDraw.show();
    }
}
