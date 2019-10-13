import React, { Component } from 'react'
import { Text, View, ART } from 'react-native'
import PropTypes from 'prop-types'
const { Surface, Shape, Path, Group } = ART

export class ArtTest extends Component {
    static propTypes = {
        outerRadius: PropTypes.number.isRequired,
        startAngle: PropTypes.number.isRequired,
        endAngle: PropTypes.number.isRequired,
        originX: PropTypes.number.isRequired,
        originY: PropTypes.number.isRequired,
        innerRadius: PropTypes.number.isRequired,
    }
    constructor(props) {
        super(props)
        this.circleRadians = Math.PI * 2
        this.radiansPerDegree = Math.PI / 180;
        this._degreesToRadians = this._degreesToRadians.bind(this)
    }
    _degreesToRadians(degrees) {
        if (degrees !== 0 && degrees % 360 === 0) {
            return this.circleRadians
        } return degrees * this.radiansPerDegree % this.circleRadians
    }
    _createCirclePath(outerRadius, innerRadius) {
        const path = Path()
        path.moveTo(0, outerRadius)
            .arc(outerRadius * 2, 0, outerRadius)
            .arc(-outerRadius * 2, 0, outerRadius)
        if (innerRadius) {
            path.moveTo((outerRadius - innerRadius), 0)
                .counterArc(innerRadius * 2, 0, innerRadius)
                .counterArc(-innerRadius * 2, 0, innerRadius)
        }
        path.close()
        return path;
    }
    _createArcPath(originX, originY, startAngle, endAngle, or, ir) {
        const path = Path()

        const sa = this._degreesToRadians(startAngle)
        var ea = this._degreesToRadians(endAngle)

        const ca = sa > ea ? this.circleRadians - sa + ea : ea = sa
        // cached sine and cosine values
        const ss = Math.sin(sa);
        const es = Math.sin(ea);
        const sc = Math.cos(sa);
        const ec = Math.cos(ea);

        // cached differences
        const ds = es - ss;
        const dc = ec - sc;
        const dr = ir - or;

        // if the angle is over pi radians (180 degrees)
        // we will need to let the drawing method know.
        const large = ca > Math.PI;
        path.move(or + or * ss, or - or * sc) // move to starting point
            .arc(or * ds, or * -dc, or, or, large) // outer arc
            .line(dr * es, dr * -ec);	// width of arc or wedge

        if (ir) {
            path.counterArc(ir * -ds, ir * dc, ir, ir, large); // inner arc
        }

        return path;
    }
    render() {
        let x = Path()
        .moveTo(0,0)
        .arc(100, 0, 50,50,true,true,1)
        // // .arc(-100,0,50)
        // // .close()
        // x.moveTo(30,0)
        // .counterArc(40,0,20)
        // // x.close()
        // let l = Path()
        // .moveTo(0,0)
        // .line(100,100)

        // return (
        //     <View>
        //         <Text> textInComponent </Text>
        //         <Surface width={300} height={250} style={{backgroundColor:'yellow'}}>
        //             <Group>
        //             <Shape d={l} stroke='red' strokeWidth={1} fill='white'/>
        //             <Shape d={x} stroke='red' strokeWidth={1} fill='#00000000'/>
        //             </Group>
        //         </Surface>
        //     </View>
        // )

        // angles are provided in degrees
        const startAngle = this.props.startAngle;
        const endAngle = this.props.endAngle;
        // if (startAngle - endAngle === 0) {
        // 	return null;
        // }

        // radii are provided in pixels
        const innerRadius = this.props.innerRadius || 0;
        const outerRadius = this.props.outerRadius;

        const { originX, originY } = this.props;

        // sorted radii
        const ir = Math.min(innerRadius, outerRadius);
        const or = Math.max(innerRadius, outerRadius);

        let path;
        if (endAngle >= startAngle + 360) {
            path = this._createCirclePath(or, ir);
        } else {
            path = this._createArcPath(originX, originY, startAngle, endAngle, or, ir);
        }

        return <Shape fill='blue' d={x} />;
    }
}

export default ArtTest
