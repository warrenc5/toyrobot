package au.com.devnull;

import au.com.devnull.ToyRobot.Command;
import au.com.devnull.ToyRobot.Orientation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/toyrobot")
public class ToyRobotController {

    @Autowired

    private ToyRobot toyRobot;

    @PostMapping("/move")
    public String move() {
        toyRobot.processCommand(Command.MOVE.toString());
        return toyRobot.report();
    }

    @PostMapping("/left")
    public String left() {
        toyRobot.processCommand(Command.LEFT.toString());
        return toyRobot.report();
    }

    @PostMapping("/right")
    public String right() {
        toyRobot.processCommand(Command.RIGHT.toString());
        return toyRobot.report();
    }

    @GetMapping("/report")
    public String report() {
        return toyRobot.report();
    }

    @PostMapping("/place")
    public String place(@RequestParam("x") int x,
            @RequestParam("y") int y,
            @RequestParam("direction") Orientation direction) {
        String placeParams = String.format("%d,%d,%s", x, y, direction);
        toyRobot.processCommand(Command.PLACE.toString() + " " + placeParams);
        return toyRobot.report();
    }
}
