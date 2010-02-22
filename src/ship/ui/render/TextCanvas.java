package ship.ui.render;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class TextCanvas {

    private final char[][] canvas;
    private final int lines;
    private final int columns;

    public TextCanvas(Integer lines, Integer columns) {
        this.lines = lines;
        this.columns = columns;
        canvas = new char[lines][columns];
        cleanCanvas();
    }

    // Helper
    private interface ForEach {

        public void each(int line, int column);
    }

    private void applyForEachCell(ForEach forEach) {
        for (int line = 0; line < lines; line++) {
            for (int column = 0; column < columns; column++) {
                forEach.each(line, column);
            }
        }
    }

    private void cleanCanvas() {
        applyForEachCell(new ForEach() {
            @Override
            public void each(int line, int column) {
                canvas[line][column] = ' ';
            }
        });
    }

    public void addText(int line, int column, String text) {
        if (line >= lines) {
            return;
        }
        for (int counter=0; counter< text.length();counter++) {
            int columnInCanvas = column+counter-1;
            if (columnInCanvas >= columns) {
                return;
            }
            canvas[line-1][columnInCanvas] = text.charAt(counter);
        }
    }

    public void drawBox(int x1, int y1, int x2, int y2) {
        for(int c=x1; c<x2; c++) {
            canvas[c-1][y1-1] = '|';
            canvas[c-1][y2-1] = '|';
        }
        for(int l=y1; l<y2; l++) {
            canvas[x1-1][l-1] = '-';
            canvas[x2-1][l-1] = '-';
        }
        canvas[x1-1][y1-1] = '+';
        canvas[x1-1][y2-1] = '+';
        canvas[x2-1][y1-1] = '+';
        canvas[x2-1][y2-1] = '+';
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(lines*columns);
        for (int line = 0; line < lines; line++) {
            sb.append(canvas[line]);
            sb.append("\n");
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        TextCanvas tc = new TextCanvas(10, 10);
        tc.drawBox(1, 1, 10, 10);
        tc.drawBox(1, 8, 10, 10);
        tc.addText(2, 2, "dois");
        System.out.println(tc);
    }

}
