package edu.uwb.braingrid.workbench.ui;

import edu.uwb.braingrid.workbench.data.SystemConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.xml.bind.SchemaOutputResolver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class InputConfigClassSelectionDialogTest {
    InputConfigClassSelectionDialog inputConfigClassSelectionDialog = new InputConfigClassSelectionDialog("", true, "");

    @Test
    public void testTemplatePaths () {
        ArrayList<ArrayList<String>> expectedResult = new ArrayList<>();
        expectedResult.add(new ArrayList<String>(Arrays.asList(
                "ParamsClassTemplateConfig/NeuronsParamsClass/AllLIFNeurons.xml",
                "ParamsClassTemplateConfig/NeuronsParamsClass/AllIZHNeurons.xml")));
        expectedResult.add(new ArrayList<String>(Arrays.asList(
                "ParamsClassTemplateConfig/SynapsesParamsClass/AllSpikingSynapses.xml",
                "ParamsClassTemplateConfig/SynapsesParamsClass/AllDSSynapses.xml",
                "ParamsClassTemplateConfig/SynapsesParamsClass/AllSTDPSynapses.xml",
                "ParamsClassTemplateConfig/SynapsesParamsClass/AllDynamicSTDPSynapses.xml")));
        expectedResult.add(new ArrayList<String>(Arrays.asList(
                "ParamsClassTemplateConfig/ConnectionsParamsClass/ConnGrowth.xml",
                "ParamsClassTemplateConfig/ConnectionsParamsClass/ConnStatic.xml")));
        expectedResult.add(new ArrayList<String>(Arrays.asList(
                "ParamsClassTemplateConfig/LayoutParamsClass/FixedLayout.xml",
                "ParamsClassTemplateConfig/LayoutParamsClass/DynamicLayout.xml")));

        ArrayList<ArrayList<String>> templatePaths = inputConfigClassSelectionDialog.getTemplatePaths();

        Assertions.assertEquals(templatePaths, expectedResult);
    }

    @Test
    public void testNodePaths () {
        inputConfigClassSelectionDialog.loadNodePaths();
        ArrayList<String> nodePaths = inputConfigClassSelectionDialog.getNodePaths();

        ArrayList<String> expectedResult = new ArrayList<>(Arrays.asList(
                "/BGSimParams/ModelParams/NeuronsParams",
                "/BGSimParams/ModelParams/SynapsesParams",
                "/BGSimParams/ModelParams/ConnectionsParams",
                "/BGSimParams/ModelParams/LayoutParams"));

        Assertions.assertEquals(nodePaths, expectedResult);
    }
}
