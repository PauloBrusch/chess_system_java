package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean testRookCastling(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;

    }

    private boolean canMove (Position position){
        ChessPiece temp = (ChessPiece) getBoard().piece(position);
        return temp == null || temp.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position temp = new Position(0,0);

        temp.setValues(position.getRow() - 1, position.getColumn());
        if (getBoard().positionExists(temp) && canMove(temp)){
            matrix[temp.getRow()][temp.getColumn()] = true;
        }

        temp.setValues(position.getRow() + 1, position.getColumn());
        if (getBoard().positionExists(temp) && canMove(temp)){
            matrix[temp.getRow()][temp.getColumn()] = true;
        }

        temp.setValues(position.getRow(), position.getColumn() - 1);
        if (getBoard().positionExists(temp) && canMove(temp)){
            matrix[temp.getRow()][temp.getColumn()] = true;
        }

        temp.setValues(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(temp) && canMove(temp)){
            matrix[temp.getRow()][temp.getColumn()] = true;
        }

        temp.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (getBoard().positionExists(temp) && canMove(temp)){
            matrix[temp.getRow()][temp.getColumn()] = true;
        }

        temp.setValues(position.getRow() - 1, position.getColumn() + 1);
        if (getBoard().positionExists(temp) && canMove(temp)){
            matrix[temp.getRow()][temp.getColumn()] = true;
        }

        temp.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (getBoard().positionExists(temp) && canMove(temp)){
            matrix[temp.getRow()][temp.getColumn()] = true;
        }

        temp.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (getBoard().positionExists(temp) && canMove(temp)){
            matrix[temp.getRow()][temp.getColumn()] = true;
        }

        // #specialmove Castling
        if (getMoveCount() == 0 && !chessMatch.getCheck()){
            // #specialmove castling kingside rook
            Position posT1 = new Position(position.getRow(), position.getColumn() +3);
            if (testRookCastling(posT1)){
                Position position1 = new Position(position.getRow(), position.getColumn() + 1);
                Position position2 = new Position(position.getRow(), position.getColumn() + 2);
                if (getBoard().piece(position1) == null && getBoard().piece(position2) == null) {
                    matrix[position.getRow()][position.getColumn() + 2] = true;
                }

            }
            // #specialmove castling queenside rook
            Position posT2 = new Position(position.getRow(), position.getColumn() -4);
            if (testRookCastling(posT2)){
                Position position1 = new Position(position.getRow(), position.getColumn() - 1);
                Position position2 = new Position(position.getRow(), position.getColumn() - 2);
                Position position3 = new Position(position.getRow(), position.getColumn() - 3);
                if (getBoard().piece(position1) == null && getBoard().piece(position2) == null && getBoard().piece(position3) == null) {
                    matrix[position.getRow()][position.getColumn() - 2] = true;
                }

            }
        }

        return matrix;
    }
}
