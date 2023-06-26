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
        if (op.tag() == Tag.PREINC) {
            // Pré-incremento
            Temp temp = new Temp(type);
            code.emitOperation(temp, e, Emitter.LIT_ONE_INT, Tag.SUM); // Geração do código LLVM para o pré-incremento
            code.emitStore(temp, expr); // Atualiza o valor da variável com o resultado do pré-incremento
        } else if (op.tag() == Tag.PREDEC) {
            // Pré-decremento
            Temp temp = new Temp(type); // Gera um nome temporário para armazenar o resultado do pré-decremento
            code.emitOperation(temp, e, Emitter.LIT_ONE_INT, Tag.SUB); // Geração do código LLVM para o pré-decremento
            code.emitStore(temp, expr); // Atualiza o valor da variável com o resultado do pré-decremento
        }
        return e;      
    }
    
    
    @Override
    public String toString() {
        return op.tag().toString();
    }
}
