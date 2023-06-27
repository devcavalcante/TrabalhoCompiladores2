package inter.expr;

import inter.Emitter;
import lexer.Tag;
import lexer.Token;

public class Prefix extends Expr {
    private Expr expr;
    
    public Prefix(Token op, Expr expr) {
        super(op, null); // O tipo do resultado será definido durante a análise semântica
        this.expr = expr;
        type = expr.type(); // O tipo resultante é o mesmo tipo da expressão interna
        addChild(expr);
    }

    public Expr gen() {
        Expr e = expr.gen();
        Temp temp = new Temp(type);
        if (op.tag() == Tag.PREINC) {
            // Pré-incremento
            code.emitOperation(temp, e, Emitter.LIT_ONE_INT, Tag.SUM);
        } else if (op.tag() == Tag.PREDEC) {
            // Pré-decremento
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
