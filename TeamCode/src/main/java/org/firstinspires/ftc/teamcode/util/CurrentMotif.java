package org.firstinspires.ftc.teamcode.util;

public class CurrentMotif
{
    public static Motif motif = Motif.UNSELECTED;
    public CurrentMotif(int tagID)
    {
        update(tagID);
    }
    public void update(int tagID)
    {
        if(tagID == 21) motif = Motif.GPP;
        else if(tagID == 22) motif = Motif.PGP;
        else if(tagID == 23) motif = Motif.PPG;
    }
}
