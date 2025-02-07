/*
 * Copyright (c) 2004-2024 Universidade do Porto - Faculdade de Engenharia
 * Laboratório de Sistemas e Tecnologia Subaquática (LSTS)
 * All rights reserved.
 * Rua Dr. Roberto Frias s/n, sala I203, 4200-465 Porto, Portugal
 *
 * This file is part of Neptus, Command and Control Framework.
 *
 * Commercial Licence Usage
 * Licencees holding valid commercial Neptus licences may use this file
 * in accordance with the commercial licence agreement provided with the
 * Software or, alternatively, in accordance with the terms contained in a
 * written agreement between you and Universidade do Porto. For licensing
 * terms, conditions, and further information contact lsts@fe.up.pt.
 *
 * Modified European Union Public Licence - EUPL v.1.1 Usage
 * Alternatively, this file may be used under the terms of the Modified EUPL,
 * Version 1.1 only (the "Licence"), appearing in the file LICENCE.md
 * included in the packaging of this file. You may not use this work
 * except in compliance with the Licence. Unless required by applicable
 * law or agreed to in writing, software distributed under the Licence is
 * distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF
 * ANY KIND, either express or implied. See the Licence for the specific
 * language governing permissions and limitations at
 * https://github.com/LSTS/neptus/blob/develop/LICENSE.md
 * and http://ec.europa.eu/idabc/eupl.html.
 *
 * For more information please see <http://lsts.fe.up.pt/neptus>.
 *
 * Author: José Pinto
 * 12/05/2016
 */
package pt.lsts.neptus.mp.preview;

import pt.lsts.neptus.mp.ManeuverLocation;
import pt.lsts.neptus.mp.SystemPositionAndAttitude;
import pt.lsts.neptus.mp.maneuvers.ScheduledGoto;
import pt.lsts.neptus.types.coord.LocationType;

/**
 * @author zp
 *
 */
public class ScheduledGotoPreview implements IManeuverPreview<ScheduledGoto> {

    protected LocationType destination;
    protected ScheduledGoto maneuver;
    protected double timeLeft;
    protected boolean finished;
    
    UnicycleModel model = new UnicycleModel();
    @Override
    public boolean init(String vehicleId, ScheduledGoto man, SystemPositionAndAttitude state, Object manState) {
        this.maneuver = (ScheduledGoto)man.clone();
        
        destination = new LocationType(man.getManeuverLocation());
        if (man.getManeuverLocation().getZUnits() == ManeuverLocation.Z_UNITS.DEPTH)
            destination.setDepth(man.getManeuverLocation().getZ());
        else if (man.getManeuverLocation().getZUnits() == ManeuverLocation.Z_UNITS.ALTITUDE)
            destination.setDepth(-man.getManeuverLocation().getZ());
        else
            destination.setDepth(0);
        
        model.setState(state);        
        timeLeft = (maneuver.getArrivalTime().getTime() - System.currentTimeMillis())/1000.0;
        finished = false;
        return true;
    }

    
    
    @Override
    public SystemPositionAndAttitude step(SystemPositionAndAttitude state, double timestep, double ellapsedTime) {
        model.setState(state);
        double distToDestination = state.getPosition().getDistanceInMeters(destination);
        
        double speed = distToDestination/(timeLeft - ellapsedTime);
        model.guide(destination, speed, destination.getDepth() >= 0 ? null : - destination.getDepth());
        model.advance(timestep);
        
        if ((timeLeft - ellapsedTime) <= 0)
            finished = true;
        
        return model.getState();
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public void reset(SystemPositionAndAttitude state) {
        model.setState(state);
    }
    
    @Override
    public Object getState() {
        return null;
    }
}
