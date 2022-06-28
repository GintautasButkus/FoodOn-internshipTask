import { Container, Nav, Navbar, NavDropdown } from "react-bootstrap"
import {Link} from "react-router-dom"
import bg from "../img/home_bg.jpg"



function LandingPageButton() {
    return <Link to="/login" className="nav-link">
        <button className ="btn btn-warning" style={{"margin-left":"-20px"}} > 
            <span style={{"fontSize":"24px"}}>
                Pradėti kelionę!
            </span>
        </button>
    </Link>
}

function LandingPageNavRegistration() {
    return <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
    <Container fluid>
    <Navbar.Brand href="#home" style={{"fontSize":"40px", padding:"0px", marginLeft:"160px"}} >food ON</Navbar.Brand>
    
      <Nav style={{"fontSize":"18px", padding:"0px"}} >
        <Nav.Link href="/login">Prisijungti</Nav.Link>
        <Nav.Link href="/register">
          Registruotis
        </Nav.Link>
      </Nav>
    
    </Container>
  </Navbar>
}

function LandingFrameMessage() {
    const style = {
        margin: "auto",
        padding: "15% 40% 0% 10%",
        color: "#484848"
    }
    return <div style={style}>
        
        
        <div style={{"fontSize": "5rem", "margin-left":"-10px", "margin-top":"-100px"}}>
            Maistas kuris veža!!
        </div>
        
        <div style={{"fontSize": "2rem"}}>
            Išsirink mėgstamiausią užkandinę.<br/>
            Susirask mėgstamiausius skonius.<br/>
            Ir važiuok patreniruoti skrandžio ten!!!<br/>
            Skanių kelionių!
        </div>
        <br />
        <LandingPageButton />
    </div>
}
function LandingFrame() {
    const style = {
        backgroundImage: "url(" + bg + ")",
        backgroundRepeat: "no-repeat",
        backgroundSize: "cover",
        position: "relative",
        height: "100vh",
        // width: "100%",
        // display: "inline-block"
    }
    return <div style={style}>
        <LandingPageNavRegistration/>
        <LandingFrameMessage />
    </div>
}
function HomePage() {
    return <div >
        
        <LandingFrame />
    </div>
}
export default HomePage