/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package djikstra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author macbookpro
 */
public class PathfinderPanel extends JPanel {
    final int maxCol = 20, maxRow = 15, nodeSize = 80;
    final int screenWidth = nodeSize * maxCol;
    final int screenHeight = nodeSize * maxRow;

    Node[][] node = new Node[maxCol][maxRow];
    Node startNode, goalNode, currentNode;
    
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkedList = new ArrayList<>();
    
    boolean goalReached = false;
    int step = 0;

    public PathfinderPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(maxRow, maxCol));
        this.addKeyListener(new Keys(this));
        this.setFocusable(true);
        
        int col = 0, row = 0;
        
        while(col < maxCol && row < maxRow) {
            node[col][row] = new Node(col, row);
            this.add(node[col][row]);
            
            col++;
            if(col == maxCol) {
                col = 0;
                row++;
            }
        }
        
        setStartNode(5, 10);
        setGoalNode(20, 15);

        setSolidNode(18, 14);
        setSolidNode(18, 15);
        setSolidNode(18, 16);
        setSolidNode(18, 17);
        setSolidNode(18, 18);
        setSolidNode(18, 19);
        setSolidNode(12, 14);
        setSolidNode(13, 14);
        setSolidNode(14, 14);
        setSolidNode(15, 14);
        setSolidNode(16, 19);
        setSolidNode(17, 19);
        setSolidNode(12, 13);
        
        setCostOnNodes();
    }
    
    private void setStartNode(int col, int row) {
        node[col][row].setAsStart();
        startNode = node[col][row];
        currentNode = startNode;
    }
    
    private void setGoalNode(int col, int row) {
        node[col][row].setAsGoal();
        goalNode = node[col][row];
    }
    
    private void setSolidNode(int col, int row) {
        node[col][row].setAsSolid();
    }
    
    private void setCostOnNodes() {
        int col = 0, row = 0;
        
        while(col < maxCol && row < maxRow) {
            getCost(node[col][row]);
            col++;
            if(col == maxCol) {
                col = 0;
                row++;
            }
        }
    }

    
    private void getCost(Node node) {
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;
        
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;
        
        node.fCost = node.gCost + node.hCost;
    }
    
    public void search() {
        if(goalReached == false && step < 300) {
            int col = currentNode.col, row = currentNode.row;
            
            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            if(row - 1 >= 0)
                openNode(node[col][row - 1]);
            if(col - 1 >= 0)
                openNode(node[col - 1][row]);
            if(row + 1 < maxRow)
                openNode(node[col][row + 1]);
            if(col + 1 < maxCol)
                openNode(node[col + 1][row]);
            
            int bestNodeIndex = 0;
            int bestNodefCost = 999;
            
            for (int i = 0; i < openList.size(); i++) {
                if(openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                } else if(openList.get(i).fCost == bestNodefCost) {
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }
            currentNode = openList.get(bestNodeIndex);
            
            if(currentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }
        }
        step++;
    }
    
    public void autoSearch() {
        while(goalReached == false && step < 300) {
            int col = currentNode.col;
            int row = currentNode.row;
            
            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);
            
            if(row - 1 >= 0)
                openNode(node[col][row - 1]);
            if(col - 1 >= 0)
                openNode(node[col - 1][row]);
            if(row + 1 < maxRow)
                openNode(node[col][row + 1]);
            if(col + 1 < maxCol)
                openNode(node[col + 1][row]);
            
            int bestNodeIndex = 0;
            int bestNodefCost = 999;
            
            for (int i = 0; i < openList.size(); i++) {
                if(openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                } else if(openList.get(i).fCost == bestNodefCost) {
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }
            currentNode = openList.get(bestNodeIndex);
            
            if(currentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }
        }
        step++; 
    }
    
    private void openNode(Node node) {
        if(node.open == false && node.checked == false && node.solid == false) {
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);
        }
    }
    
    private void trackThePath() {
        Node current = goalNode;
        int cost = 0;
        
        while(current != startNode) {
            current = current.parent;
            cost++;
            if(current != startNode) {
                current.setAsPath();
            }
        }
        
        JOptionPane.showConfirmDialog(this, "El costo total del recorrido fue de " + cost + " movimientos", "MEJOR RUTA ENCONTRADA", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }
    
}