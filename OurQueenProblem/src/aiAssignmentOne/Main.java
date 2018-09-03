package aiAssignmentOne;

import javax.swing.JOptionPane;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import aiAssignmentOne.SolverUtils;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Main extends JFrame {
	
	
	
	
	private int select = 1;

	private JPanel contentPane;
	private JTextField textField_NumOfQueens;
	private JTextField textField_PopSize;
	private JTextField textField_CrossProb;
	private JTextField textField_NumOfGen;
	private JTextField textField_LBS_NumOfStates;
	private JTextField textField_SA_Temp;
	private JTextField textField_SA_CoolFact;
	private JTextField textField_PercElit;
	private JTextField textField_MutProb;
	private int n = 4;
	private int[] queenArray;
	private JPanel[] elements = new JPanel[n*n];
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setTitle("N-Queens problem solver by Arvydas Palaima");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 983, 685);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel MainBody = new JPanel();
		MainBody.setBounds(0, 0, 965, 638);
		contentPane.add(MainBody);
		MainBody.setLayout(null);
		
		JPanel Methods = new JPanel();
		Methods.setBounds(12, 13, 329, 600);
		MainBody.add(Methods);
		Methods.setLayout(null);		
		
		JLabel lblSelectSolvingMethod = new JLabel("Select solving method");
		lblSelectSolvingMethod.setFont(new Font("Gadugi", Font.PLAIN, 15));
		lblSelectSolvingMethod.setBounds(12, 0, 305, 27);
		Methods.add(lblSelectSolvingMethod);
		
		JPanel Parameters = new JPanel();
		Parameters.setBounds(12, 88, 305, 395);
		Methods.add(Parameters);
		Parameters.setLayout(new CardLayout(0, 0));
		
		JPanel HillClimbing = new JPanel();
		HillClimbing.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		HillClimbing.setLayout(null);
		Parameters.add(HillClimbing, "name_804026294264128");
		
		JPanel LocalBeamSearch = new JPanel();
		LocalBeamSearch.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		LocalBeamSearch.setLayout(null);
		Parameters.add(LocalBeamSearch, "name_804102609702173");
		
		JLabel lblNumberOfIterations_1 = new JLabel("Number of states");
		lblNumberOfIterations_1.setFont(new Font("Gadugi", Font.PLAIN, 17));
		lblNumberOfIterations_1.setBounds(12, 13, 192, 19);
		LocalBeamSearch.add(lblNumberOfIterations_1);
		
		textField_LBS_NumOfStates = new JTextField();
		textField_LBS_NumOfStates.setColumns(10);
		textField_LBS_NumOfStates.setBounds(12, 38, 64, 22);
		LocalBeamSearch.add(textField_LBS_NumOfStates);
		
		JPanel SimulatedAnnealing = new JPanel();
		SimulatedAnnealing.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		SimulatedAnnealing.setLayout(null);
		Parameters.add(SimulatedAnnealing, "name_804218869004725");
		
		JLabel Temperature = new JLabel("Temperature");
		Temperature.setFont(new Font("Gadugi", Font.PLAIN, 17));
		Temperature.setBounds(12, 13, 192, 19);
		SimulatedAnnealing.add(Temperature);
		
		textField_SA_Temp = new JTextField();
		textField_SA_Temp.setColumns(10);
		textField_SA_Temp.setBounds(12, 38, 64, 22);
		SimulatedAnnealing.add(textField_SA_Temp);
		
		JLabel lblCoolingFactor = new JLabel("Cooling factor");
		lblCoolingFactor.setFont(new Font("Gadugi", Font.PLAIN, 17));
		lblCoolingFactor.setBounds(12, 127, 192, 19);
		SimulatedAnnealing.add(lblCoolingFactor);
		
		textField_SA_CoolFact = new JTextField();
		textField_SA_CoolFact.setColumns(10);
		textField_SA_CoolFact.setBounds(12, 152, 64, 22);
		SimulatedAnnealing.add(textField_SA_CoolFact);
		
		JPanel GeneticAlgorithm = new JPanel();
		GeneticAlgorithm.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Parameters.add(GeneticAlgorithm, "name_803624841002390");
		GeneticAlgorithm.setLayout(null);
		
		JLabel lblPopulationSize = new JLabel("Population size");
		lblPopulationSize.setBounds(12, 13, 192, 19);
		lblPopulationSize.setFont(new Font("Gadugi", Font.PLAIN, 17));
		GeneticAlgorithm.add(lblPopulationSize);
		
		textField_PopSize = new JTextField();
		textField_PopSize.setBounds(12, 38, 64, 22);
		GeneticAlgorithm.add(textField_PopSize);
		textField_PopSize.setColumns(10);
		
		JLabel lblMutationProbability = new JLabel("Crossover probability");
		lblMutationProbability.setFont(new Font("Gadugi", Font.PLAIN, 17));
		lblMutationProbability.setBounds(12, 166, 192, 19);
		GeneticAlgorithm.add(lblMutationProbability);
		
		textField_CrossProb = new JTextField();
		textField_CrossProb.setColumns(10);
		textField_CrossProb.setBounds(12, 191, 64, 22);
		GeneticAlgorithm.add(textField_CrossProb);
		
		JLabel lblGenerationSize = new JLabel("Number of generations");
		lblGenerationSize.setFont(new Font("Gadugi", Font.PLAIN, 17));
		lblGenerationSize.setBounds(12, 335, 192, 19);
		GeneticAlgorithm.add(lblGenerationSize);
		
		textField_NumOfGen = new JTextField();
		textField_NumOfGen.setColumns(10);
		textField_NumOfGen.setBounds(12, 360, 64, 22);
		GeneticAlgorithm.add(textField_NumOfGen);
		
		JLabel lblPercentOfElitism = new JLabel("Percent of elitism");
		lblPercentOfElitism.setFont(new Font("Gadugi", Font.PLAIN, 17));
		lblPercentOfElitism.setBounds(12, 87, 192, 19);
		GeneticAlgorithm.add(lblPercentOfElitism);
		
		textField_PercElit = new JTextField();
		textField_PercElit.setColumns(10);
		textField_PercElit.setBounds(12, 112, 64, 22);
		GeneticAlgorithm.add(textField_PercElit);
		
		JLabel label_1 = new JLabel("Mutation probability");
		label_1.setFont(new Font("Gadugi", Font.PLAIN, 17));
		label_1.setBounds(12, 249, 192, 19);
		GeneticAlgorithm.add(label_1);
		
		textField_MutProb = new JTextField();
		textField_MutProb.setColumns(10);
		textField_MutProb.setBounds(12, 274, 64, 22);
		GeneticAlgorithm.add(textField_MutProb);
		
		textField_NumOfQueens = new JTextField();
		textField_NumOfQueens.setBounds(56, 537, 85, 22);
		Methods.add(textField_NumOfQueens);
		textField_NumOfQueens.setColumns(10);
		
		JLabel lblNumberOfQueens = new JLabel("Number of queens");
		lblNumberOfQueens.setBounds(22, 508, 146, 16);
		Methods.add(lblNumberOfQueens);
		lblNumberOfQueens.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfQueens.setFont(new Font("Gadugi", Font.PLAIN, 15));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(UIManager.getColor("Button.disabledForeground"));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num;
				num = comboBox.getSelectedIndex();
				if (num == 0) {
				 HillClimbing.setVisible(true);
				 LocalBeamSearch.setVisible(false);
				 SimulatedAnnealing.setVisible(false);
				 GeneticAlgorithm.setVisible(false);
				 select = 1;
				} 
				if (num == 1) {
				 HillClimbing.setVisible(false);
				 LocalBeamSearch.setVisible(true);
				 SimulatedAnnealing.setVisible(false);
				 GeneticAlgorithm.setVisible(false);
				 select = 2;
				}
				if (num == 2) {
				 HillClimbing.setVisible(false);
				 LocalBeamSearch.setVisible(false);
				 SimulatedAnnealing.setVisible(true);
				 GeneticAlgorithm.setVisible(false);
				 select = 3;
				}
				if (num == 3) {
				 HillClimbing.setVisible(false);
				 LocalBeamSearch.setVisible(false);
				 SimulatedAnnealing.setVisible(false);
				 GeneticAlgorithm.setVisible(true);
				 select = 4;
				}
			}
		
		});
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Hill Climbing", "Beam Search", "Simulated Annealing", "Genetic Algorithm"}));
		comboBox.setFont(new Font("Gadugi", Font.BOLD, 15));
		comboBox.setBounds(12, 33, 305, 42);
		Methods.add(comboBox);
		
		JButton btnSolve = new JButton("SOLVE");
		btnSolve.setBounds(183, 508, 134, 79);
		Methods.add(btnSolve);
		btnSolve.setForeground(new Color(255, 255, 255));
		btnSolve.setBackground(new Color(0, 128, 0));
		btnSolve.setFont(new Font("Garamond", Font.BOLD, 18));
		btnSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((select == 1) 
						&&(!textField_NumOfQueens.getText().isEmpty())) {
					int NumOfQueens = Integer.parseInt(textField_NumOfQueens.getText());        //Hill Climbing parameters
					n = NumOfQueens;
					hillClimbingAlgorithm h = new hillClimbingAlgorithm();
					queenArray = h.solve(NumOfQueens);
					if (queenArray == null) {
						Main.infoBox("Solution not found. Try adjusting parameters or try again", "Solving");
					}
				}
				else if ((select == 2)
						&&(!textField_LBS_NumOfStates.getText().isEmpty())
						&&(!textField_NumOfQueens.getText().isEmpty())){
					int LBSNumOfStates = Integer.parseInt(textField_LBS_NumOfStates.getText()); //Local beam search parameters
					int NumOfQueens = Integer.parseInt(textField_NumOfQueens.getText());
					n = NumOfQueens;
					localBeamSearchAlgo l = new localBeamSearchAlgo();
					queenArray = l.solve(NumOfQueens, LBSNumOfStates);
					if (queenArray == null) {
						Main.infoBox("Solution not found. Try adjusting parameters or try again", "Solving");
					}
				}
				else if ((select == 3)
						&&(!textField_SA_Temp.getText().isEmpty())
						&&(!textField_SA_CoolFact.getText().isEmpty())
						&&(!textField_NumOfQueens.getText().isEmpty())){
					int SATemp= Integer.parseInt(textField_SA_Temp.getText());                  //Simulated annealing parameters
					double SACoolFact = Double.parseDouble(textField_SA_CoolFact.getText());    //Simulated annealing parameters
					int NumOfQueens = Integer.parseInt(textField_NumOfQueens.getText());
					n = NumOfQueens;
					simulatedAnnealingAlgo s = new simulatedAnnealingAlgo();
					queenArray = s.solve(NumOfQueens, SATemp, SACoolFact);
					if (queenArray == null) {
						Main.infoBox("Solution not found. Try adjusting parameters or try again", "Solving");
					}
				}
				else if ((select == 4)
						&&(!textField_PopSize.getText().isEmpty())
						&&(!textField_PercElit.getText().isEmpty())
						&&(!textField_CrossProb.getText().isEmpty())
						&&(!textField_MutProb.getText().isEmpty())
						&&(!textField_NumOfGen.getText().isEmpty())
						&&(!textField_NumOfQueens.getText().isEmpty())){
					int GPopSize = Integer.parseInt(textField_PopSize.getText());               //Genetic algorithm parameters
					double GPercElit = Double.parseDouble(textField_PercElit.getText());        //Genetic algorithm parameters
					double GCrossProb = Double.parseDouble(textField_CrossProb.getText()); 		//Genetic algorithm parameters
					double GMutProb = Double.parseDouble(textField_MutProb.getText()); 			//Genetic algorithm parameters
					int GNumOfGen = Integer.parseInt(textField_NumOfGen.getText());				//Genetic algorithm parameters
					int NumOfQueens = Integer.parseInt(textField_NumOfQueens.getText());        //Genetic algorithm parameters
					n = NumOfQueens;
					genAlgorithm g = new genAlgorithm();
					queenArray = g.solve(NumOfQueens, GPopSize, GMutProb, GCrossProb, GPercElit, GNumOfGen);
					if (queenArray == null) {
						Main.infoBox("Solution not found. Try adjusting parameters or try again", "Solving");
					}
				}
				else {
					Main.infoBox("Please input all of the required parameters", "Parameters");
				}
				MainBody.removeAll();
				MainBody.add(Methods);
				MainBody.add(drawChessBoard(queenArray));
				MainBody.revalidate();
				MainBody.repaint();
			}
		});
	
	
		MainBody.add(drawChessBoard(SolverUtils.generateRandomState(n)));
	
	}

public static void infoBox(String infoMessage, String titleBar)
	    {
	        JOptionPane.showMessageDialog(null, infoMessage, "Error: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	    }	
	
public JPanel drawChessBoard(int[] queenPositionArray) {
		
		elements = new JPanel[n*n];
		
		JPanel board = new JPanel();
		board.setBounds(353, 13, 600, 600);
		int elementSize = (int) board.getHeight()/n;
		board.setBackground(Color.WHITE);
		board.setLayout(null);
		

		for(int z=0;z<n;z++) {
			board.add(drawQueens(z,queenPositionArray[z],elementSize));
		}
		
		boolean w = false;
		Color c;
		
		for(int i = 0; i<n;i++) {
			for(int j = 0; j < n;j++) {
			elements[i] = new JPanel();
			elements[i].setBounds(i*elementSize, j*elementSize, elementSize, elementSize);
			
			if(w) {
				c = Color.LIGHT_GRAY;
				w = false;
			}
			else {
				c = Color.WHITE;
				w = true;
			}
			elements[i].setBackground(c);
			board.add(elements[i]);
			}
			if(w && (n%2)==0) {
				w=false;
				
			}
			else if(!w && (n%2)==0){
				w=true;
			}
		}
		board.revalidate();
		board.repaint();
		return board;
	}
	
	public JLabel drawQueens(int x, int y,int elementSize) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("C:\\Users\\Selcuk\\source\\repos\\AI_Examples\\OurQueenProblem\\src\\aiAssignmentOne\\queen.png"));
		    
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(elementSize, elementSize, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		
		
		
		JLabel image = new JLabel();
		image.setIcon(imageIcon);
		image.setBounds(x*elementSize,y*elementSize, elementSize, elementSize);
		
		return image;
	}
}