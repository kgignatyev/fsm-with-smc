package com.xpansiv.demo.fsm.asset_transfer;

import org.apache.commons.io.IOUtils;
import org.junit.Test;


public class TransferAssetsHandlerTest {

    public static final String SENDER = "SENDER";
    public static final String RECIPIENT = "RECIPIENT";

    @Test
    public void testWrongTransitionFSM() throws Exception{
        TransferAssetsHandler dh = new TransferAssetsHandler(SENDER, RECIPIENT, "EIN123", 200L);
        TransferAssetsContext decisionContext = new TransferAssetsContext(dh);
        System.out.println( decisionContext.getState() );
        visualizeState( decisionContext, "target/state0.png");
        decisionContext.LockSenderFunds();
        visualizeState( decisionContext, "target/state1.png");
        decisionContext.InitiateTransfer();
        visualizeState( decisionContext, "target/state2.png");
        decisionContext.ReceivedByRecepient();
        visualizeState( decisionContext, "target/state3.png");

    }

    private void visualizeState(TransferAssetsContext decisionContext, String pngFile) throws Exception {
        String dotFileName = "target/generated-sources/smc/com/xpansiv/demo/fsm/asset_transfer/TransferAssets_sm.dot";
        String stateName = decisionContext.getState().getName().split("[.]")[1];
        Process p =Runtime.getRuntime().exec(new String[]{"docker", "run", "--rm",
                "-v", System.getProperty("user.dir")+":/dot/data",
                "xpansiv.jfrog.io/default-docker-virtual/xpansiv-smcdot:1",
                "/usr/bin/dot_file2png.py",
                "--in", "data/"+ dotFileName,
                "--out", "data/" + pngFile,
                "--state", stateName});
        p.waitFor();

        String output = IOUtils.toString(p.getInputStream());
        String errorOutput = IOUtils.toString(p.getErrorStream());
        System.out.println( output );
        System.err.println( errorOutput );
        if( p.exitValue()!= 0){
            throw new Exception("png generation error");
        }
    }
}
