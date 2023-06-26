package inter.expr;

import inter.Emitter;
import lexer.Tag;
import lexer.Token;

public class Postfix extends Expr {
    private Expr expr;
    
    public Postfix(Token op, Expr expr) {
        super(op, null); 
        this.expr = expr;
        type = expr.type(); 
        addChild(expr);
    }

    public Expr gen() {
        Expr e = expr.gen();
        if (op.tag() == Tag.POSINCREMENT) {
            // Pós-incremento
            Temp temp = new Temp(type);
            code.emitOperation(temp, e, Emitter.LIT_ONE_INT, Tag.SUM); 
            code.emitStore(expr, temp); 
        } else if (op.tag() == Tag.POSDECREMENT) {
            // Pós-decremento
            Temp temp = new Temp(type);
            code.emitOperation(temp, e, Emitter.LIT_ONE_INT, Tag.SUB); 
            code.emitStore(expr, temp); 
        }
        return e;   
    }
    
    
    @Override
    public String toString() {
        return op.tag().toString();
    }
}
