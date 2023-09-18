package protocol;

import server.Server;
import server.db.outsideaddressing.NodeOutsideAddressing;
import server.entities.Vehicle;

public class Protocol {
    private Server server;

    private int responseINT = -1;
    private NodeOutsideAddressing responseNo = null;
    private Boolean responseBool = false;

    public Protocol(int option) {
        this.server = new Server(option);
    }

    public void request(int type, Vehicle data) {
        switch (type) {
            case 0: 
                // inserir
                this.server.save(data);
                break;
            case 1:
                this.server.remove(data.getRenavam());
                break;
            case 2: 
                this.server.getAll();
                break;
            case 3: 
                this.server.remove(data.getRenavam());
                break;
            case 4:
                responseNo = this.server.getByLicensePlate(data.getLicencePlate());
                break;
            case 6: 
                // responseINT = this.server.getAllNodes();
                break;
            case 7: 
                responseBool = this.server.update(data.getRenavam(), data);
                break;
            case 8: 
                responseNo = this.server.getOne(data.getRenavam());
                break;

        }
    }

    public int response() {
        return this.responseINT;
    }
    
    public NodeOutsideAddressing responseNo() {
        return this.responseNo;
    }

    public Boolean responseBool() {
        return this.responseBool;
    }

}
