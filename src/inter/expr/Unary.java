package inter.expr;

import inter.Emitter;
import lexer.Tag;
import lexer.Token;

public class Unary extends Expr {
    private Expr expr1;

    public Unary(Token op, Expr expr) {
        super(op, null); // O tipo do resultado será definido durante a análise semântica
        type = expr.type(); // O tipo resultante é o mesmo tipo da expressão interna
		expr1 = expr;
    }
    
    @Override
    public Expr gen() {
        Temp t = new Temp(type);
        Expr e = expr1.gen();
        
        if (op.tag() == Tag.MINUS) {
            code.emitOperation(t, Emitter.LIT_ZERO_INT, e, Tag.SUB); // Geração do código LLVM para operador unário -
        } else if (op.tag() == Tag.PLUS) {
            code.emitStore(t, e); // Geração do código LLVM para operador unário +
        }
        
        return t;
    }

    @Override
    public String toString() {
        return op.tag().toString() + " " + expr1.toString();
    }
}
