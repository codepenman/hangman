/**
 * Write a description of class Position here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum Position  
{
    A(1100, 500),
    B(1150, 500),
    C(1200, 500),
    D(1250, 500),
    E(1300, 500),
    F(1350, 500),
    G(1400, 500),
    H(1100, 550),
    I(1150, 550),
    J(1200, 550),
    K(1250, 550),
    L(1300, 550),
    M(1350, 550),
    N(1400, 550),
    O(1100, 600),
    P(1150, 600),
    Q(1200, 600),
    R(1250, 600),
    S(1300, 600),
    T(1350, 600),
    U(1400, 600),
    V(1100, 650),
    W(1150, 650),
    X(1200, 650),
    Y(1250, 650),
    Z(1300, 650);
    
    public final int x;
    public final int y;
    
    private Position(int x, int y)   {
        this.x = x;
        this.y = y;
    }
}
