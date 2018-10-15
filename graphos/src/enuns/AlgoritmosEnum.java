package enuns;

public enum AlgoritmosEnum {
	
	DIJSTRA("Dijkstra"),
	ALGORITMO_PRIM("AlgoritmoPrim"),
	DFS("AlgDFS"),
	BFS("AlgBFS");
	
	private String description;
	
	private AlgoritmosEnum(String description) {
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	public static String getAlgoritmo(AlgoritmosEnum arg){
		for (AlgoritmosEnum algoritmo: AlgoritmosEnum.values()){
			if (algoritmo.getDescription().equals((arg.getDescription()))){
				return arg.getDescription();
			}
		}
		return "";
	}
	
}
