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
        Temp temp = new Temp(type);
        code.emitLoad(temp, e);
        if (op.tag() == Tag.POSINCREMENT) {
            // Pós-incremento
            code.emitOperation(temp, e, Emitter.LIT_ONE_INT, Tag.SUM); 
        } else if (op.tag() == Tag.POSDECREMENT) {
            // Pós-decremento
            code.emitOperation(temp, e, Emitter.LIT_ONE_INT, Tag.SUB); 
        }
        code.emitStore(expr, temp);
        return e;   
    }
    
    
    @Override
    public String toString() {
        return op.tag().toString();
    }
}
