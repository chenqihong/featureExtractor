import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.comments.Comment;
import com.opencsv.CSVWriter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class featureExtractor {
    public static int getNumberLines(String fileName){
        Path path = Paths.get(fileName);
        int numLines = 0;
        try{
            numLines = (int) Files.lines(path).count();
        } catch (IOException e) {
           e.printStackTrace();
        }
        return numLines;
    }

    public static int getTotalLinesCommentCount(CompilationUnit compilationUnit){
        List<Comment> commentList = compilationUnit.getAllComments();
        int commentLinesCount = 0;
        for (Comment comment: commentList){
            String[] lines = comment.toString().split("\\r?\\n");
            commentLinesCount += lines.length;
        }
        return commentLinesCount;
    }

    public static List<String> collectStats(String fileName) throws FileNotFoundException {
        List<String> statsList = new ArrayList<>();
        int numLines = getNumberLines(fileName);
        statsList.add(String.valueOf(numLines));

        CompilationUnit compilationUnit = StaticJavaParser.parse(new File(fileName));
        int numLinesComment = getTotalLinesCommentCount(compilationUnit);
        statsList.add(String.valueOf(numLinesComment));

        IfStmtVisit ifStmtVisitor = new IfStmtVisit();
        ifStmtVisitor.visit(compilationUnit, null);
        int numIfStatement = IfStmtVisit.count;
        statsList.add(String.valueOf(numIfStatement));

        whileLoopVisit whileLoopVisitor = new whileLoopVisit();
        whileLoopVisitor.visit(compilationUnit, null);
        int numWhileStatement = whileLoopVisit.count;
        statsList.add(String.valueOf(numWhileStatement));

        doWhileLoopVisit doWhileLoopVisitor = new doWhileLoopVisit();
        doWhileLoopVisitor.visit(compilationUnit, null);
        int numDoWhileStatement = doWhileLoopVisit.count;
        statsList.add(String.valueOf(numDoWhileStatement));

        forLoopVisit forLoopVisitor = new forLoopVisit();
        forLoopVisitor.visit(compilationUnit, null);
        int numForLoopStatement = forLoopVisit.count;
        statsList.add(String.valueOf(numForLoopStatement));

        returnVisit returnVisitor = new returnVisit();
        returnVisitor.visit(compilationUnit, null);
        int numReturnStatement = returnVisit.count;
        statsList.add(String.valueOf(numReturnStatement));


        parametersVisit parametersVisitor = new parametersVisit();
        parametersVisitor.visit(compilationUnit, null);
        int numParameters = parametersVisit.count;
        statsList.add(String.valueOf(numParameters));

        declareVariableVisit variableDeclareVisitor = new declareVariableVisit();
        variableDeclareVisitor.visit(compilationUnit, null);
        int numDeclareVariables = declareVariableVisit.count;
        statsList.add(String.valueOf(numDeclareVariables));


        methodDeclareVisit methodDeclareVisitor = new methodDeclareVisit();
        methodDeclareVisitor.visit(compilationUnit, null);
        int numDefaultCount = methodDeclareVisit.defaultCount;
        int numPublicCount = methodDeclareVisit.publicCount;
        int numPrivateCount = methodDeclareVisit.privateCount;
        int numProtectedCount = methodDeclareVisit.protectedCount;
        statsList.add(String.valueOf(numDefaultCount));
        statsList.add(String.valueOf(numPublicCount));
        statsList.add(String.valueOf(numPrivateCount));
        statsList.add(String.valueOf(numProtectedCount));
        return statsList;
    }

    public static void cleanCounters(){
        declareVariableVisit.count = 0;
        doWhileLoopVisit.count = 0;
        forLoopVisit.count = 0;
        IfStmtVisit.count = 0;
        methodDeclareVisit.publicCount = 0;
        methodDeclareVisit.protectedCount = 0;
        methodDeclareVisit.privateCount = 0;
        methodDeclareVisit.defaultCount = 0;
        parametersVisit.count = 0;
        returnVisit.count = 0;
        whileLoopVisit.count = 0;
    }

    public static void writeToCSV(String fileName, List<String> statsList) throws IOException {
        File file = new File("/Users/qihongchen/Desktop/result.csv");
        FileWriter outputFile = new FileWriter(file);
        CSVWriter writer = new CSVWriter(outputFile);
        String[] header = { "fileDir", "numLines", "numLinesComment", "numIfStatement", "numWhileStatement", "numDoWhileStatement", "numForLoopStatement", "numReturnStatement", "numParameters", "numDeclareVariables", "numDefaultCount", "numPublicCount", "numPrivateCount", "numProtectedCount"};
        writer.writeNext(header);
        statsList.add(0, fileName);
        String [] statsArray = new String[statsList.size()];
        statsArray = statsList.toArray(statsArray);
        writer.writeNext(statsArray);
        writer.close();
    }


    public static void main(String [] args) throws IOException {
        String fileName = args[0];
        cleanCounters();
        List<String> statsList = collectStats(fileName);
        System.out.println(statsList);
        writeToCSV(fileName, statsList);
    }
}
