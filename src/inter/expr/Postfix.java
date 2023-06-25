package inter.expr;

import inter.Emitter;
import lexer.Tag;
import lexer.Token;

public class Postfix extends Expr {
    private Expr expr;
    
    public Postfix(Token op, Expr expr) {
        super(op, null); // O tipo do resultado será definido durante a análise semântica
        this.expr = expr;
        type = expr.type(); // O tipo resultante é o mesmo tipo da expressão interna
    }

    public Expr gen() {
        Expr e = expr.gen();
        if (op.tag() == Tag.POSINCREMENT) {
            // Pós-incremento
            Temp temp = new Temp(type);
            code.emitOperation(temp, e, Emitter.LIT_ONE_INT, Tag.SUM); // Geração do código LLVM para o pós-incremento
            code.emitStore(expr, temp); // Atualiza o valor da variável com o resultado do pós-incremento
        } else if (op.tag() == Tag.POSDECREMENT) {
            // Pós-decremento
            Temp temp = new Temp(type); // Gera um nome temporário para armazenar o resultado do pós-decremento
            code.emitOperation(temp, e, Emitter.LIT_ONE_INT, Tag.SUB); // Geração do código LLVM para o pós-decremento
            code.emitStore(expr, temp); // Atualiza o valor da variável com o resultado do pós-decremento
        }
        return e;   
    }
    
    
    @Override
    public String toString() {
        return op.toString() + expr.toString();
    }
}
