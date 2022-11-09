import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class declareVariableVisit extends VoidVisitorAdapter<Void> {
    static int count = 0;
    public void visit(VariableDeclarator r, Void arg)
    {
        count += 1;
        System.out.println("Found a variable @ " + r.toString());
    }
}
