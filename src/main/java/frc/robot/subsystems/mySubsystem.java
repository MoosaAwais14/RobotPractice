package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class mySubsystem extends SubsystemBase {

    // this subsystem, in theory, handles the game piece. It will have two parts. It
    // has motors that can shoot out/intake a ball. It also has pistons that
    // raise/lower the subsystem/that part of the bot
    // as such, we need to make variables that hold these acutators/components (the
    // motors in thsi case) for us

    // these are private because we only want to deal with these motors in this
    // specific class. no where else

    // What goes in as an argument: port number where the motor controller is
    // plugged into on the RoboRIO
    private Spark leftMotor = new Spark(1);
    private Spark rightMotor = new Spark(2);

    // methods below

    public void setPower(double speed) { // this method sets the speed of both motors
        /*
         * we have one method that sets speeds for both motors because both left and
         * right motors need to be changing simultaneusly
         * this will also make it easier for our commands to interact with thesefunctions
         * this method would likely be called elsewhere in the code, prolly in a class
           where the controlls are defined and the speed of the motors is acc changing
         * call setPower() when the joystick is moved. it would adjust the motor speed
         * based on that input.
         */

        /*
         * motor speeds go from -1 to 1
         * 1 is forward -1 is backwards
         * it's a vector. negative values don't necessarily indicate negative magnitude,
         * but the direction of the object
         */

        /*
         * controller (in theory) indicates motor speed
         * moving joystick up indicates a speed of 1
         * moving joystick down indicates a speed of -1
         * sometimes, when the joystick is right in the middle, the speed won't exactly
           be at 0. it might be at 0.267, or -0.198, etc
         * to account for this descrepency, we do the following
         */

        if (Math.abs(speed) > 0.1) { // this is to account for the slight descrepency for if the controller isn't
                                     // exactly center, but appears centered
            leftMotor.set(speed);
            rightMotor.set(-speed); // this is negative because it should be moving in the opposite direction of the
                                    // the left motor, because when we are shooting the game piece out, the left and
                                    // right motors should be spinning in the opposit direction
        } else
            stop(); // stops both motors wen the joystick IS in the center

    }

    public void stop() { // setting motor speed to static
        leftMotor.set(0);
        rightMotor.set(0);
    }

    /*
     * It is convenient to have a getSpeed method so that we can return the speed of
     * the motors. This is useful for keeping track of systems state and robot
     * status
     * We don't have any variables that are keeping track of the speed, but the
     * motor objects remember the speed that we put into them using the set method
     * using .get can help you get the motor speed that was put into the .set
     * 
     */
    public double getSpeed() { // gets the motor speed
        return leftMotor.get(); // we have leftMotor here, because the magnitude of both motors' speed is the
                                // same. the only difference is the direction. we chose left because it is
                                // positive.
        // moving forward, if we ever need to get the motor speed of the right motor, we just needa do this: -obj.getSpeed();
    }
}
