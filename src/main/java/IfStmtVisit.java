import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class IfStmtVisit extends VoidVisitorAdapter<Void> {

    static int count = 0;
    @Override
    public void visit(IfStmt n, Void arg)
    {
        count ++;
        System.out.println("\nFound an if statement @ " + n.getBegin());
        System.out.println("n = " + n);
    }
}


