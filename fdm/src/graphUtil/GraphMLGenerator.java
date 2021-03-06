package graphUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import com.tinkerpop.blueprints.util.io.graphml.GraphMLTokens;
import com.tinkerpop.blueprints.util.io.graphml.GraphMLWriter;

public class GraphMLGenerator
{

	Graph graph = new TinkerGraph();
	int index;

	public GraphMLGenerator (Graph g, int i)
	{
		graph = g;
		index = i;
	}


	/**
	 *  Creates a graphML file with the data of all players on a frame.
	 */
	
	public void generateGraphML()
	{
		String filename = "Frame" + (index) +".graphml";
		Map<String, String> vertexKeyTypes = new HashMap<String, String>();
		vertexKeyTypes.put("number", GraphMLTokens.INT);
		vertexKeyTypes.put("xPosition", GraphMLTokens.FLOAT);
		vertexKeyTypes.put("yPosition", GraphMLTokens.FLOAT);
		vertexKeyTypes.put("team",GraphMLTokens.STRING);
		vertexKeyTypes.put("ballCarrier", GraphMLTokens.BOOLEAN);

		GraphMLWriter writer = new GraphMLWriter(graph);
		writer.setVertexKeyTypes(vertexKeyTypes);

		writer.setNormalize(true);
		try {
			writer.outputGraph("graphML\\" + filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		graph.shutdown();

	}





}
