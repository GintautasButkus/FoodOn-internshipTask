import { useRef, useState, useEffect } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import '../styles/Register.css'
import {
  faCheck,
  faTimes,
  faInfoCircle,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import axios from "../api/axios";
import Button from "react-bootstrap/Button";
import { Form } from "react-bootstrap";
import { Link } from "react-router-dom";
import bg from "../img/registration_bg.jpg"


const USER_REGEX = /^[A-z][A-z0-9-_]{3,23}$/;
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;
const EMAIL_NAME_REGEX = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,})+$/;
// const ROLE_REGEX = /^[A-z]{4,15}$/;
const REGISTER_URL = "/api/auth/user/signup";

const Register = () => {
  const userRef = useRef();
  const errRef = useRef();

  const [userName, setUser] = useState("");
  const [validName, setValidName] = useState(false);
  const [userFocus, setUserFocus] = useState(false);

  const [userPassword, setPwd] = useState("");
  const [validPwd, setValidPwd] = useState(false);
  const [pwdFocus, setPwdFocus] = useState(false);

  const [email, setEmail] = useState("");
  const [validEmail, setValidEmail] = useState(false);
  const [emailFocus, setEmailFocus] = useState(false);

  // const [role, setRole] = useState("");
  // const [validRole, setValidRole] = useState(false);
  // const [roleFocus, setRoleFocus] = useState(false);

  const [matchPwd, setMatchPwd] = useState("");
  const [validMatch, setValidMatch] = useState(false);
  const [matchFocus, setMatchFocus] = useState(false);

  const [errMsg, setErrMsg] = useState("");
  const [success, setSuccess] = useState(false);

  useEffect(() => {
    userRef.current.focus();
  }, []);

  useEffect(() => {
    const result = USER_REGEX.test(userName);
    console.log(result);
    console.log(userName);
    setValidName(result);
  }, [userName]);

  useEffect(() => {
    const result = EMAIL_NAME_REGEX.test(email);
    console.log(result);
    console.log(email);
    setValidEmail(result);
  }, [email]);

  // useEffect(() => {
  //   const result = ROLE_REGEX.test(role);
  //   console.log(result);
  //   console.log(role);
  //   setValidRole(result);
  // }, [role]);

  useEffect(() => {
    const result = PWD_REGEX.test(userPassword);
    console.log(result);
    console.log(userPassword);
    setValidPwd(result);
    const match = userPassword === matchPwd;
    setValidMatch(match);
  }, [userPassword, matchPwd]);

  useEffect(() => {
    setErrMsg("");
  }, [userName, userPassword, matchPwd]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    // if button enabled with JS hack
    const v1 = USER_REGEX.test(userName);
    const v2 = PWD_REGEX.test(userPassword);
    const v3 = EMAIL_NAME_REGEX.test(email);
    // const v4 = ROLE_REGEX.test(role);
    if (!v1 || !v2 || !v3) {
      setErrMsg("Neteisingai suvesti duomenys");
      return;
    }

    try {
      const response = await axios.post(
        REGISTER_URL,
        JSON.stringify({ username: userName, password: userPassword, email }),
        {
          headers: {
            "Content-Type": "application/json",
          },
          withCredentials: false,
        }
      );
      console.log(response.data);
      console.log(response.accessToken);
      console.log(JSON.stringify(response));
      setSuccess(true);
      // clear input fields
    } catch (error) {
      if (!error?.response) {
        setErrMsg("Ryšio problemos");
      } else if (error.response?.status === 409) {
        setErrMsg("Toks vartotojas jau užsiregistravęs");
      } else {
        setErrMsg("Registracija nepavyko");
      }
      errRef.current.focus();
    }
  };

  const style = {
    backgroundImage: "url(" + bg + ")",
    backgroundRepeat: "no-repeat",
    backgroundSize: "cover",
    position: "relative",
    height: "100vh",
    "margin-top":"-170px"
    // width: "100%",
    // display: "inline-block"
  }

  return (
    <div style={style}>
      {success ? (
        <section className="hello">
          <h1>Sveiki, {userName}!</h1>
          <p>
            <Link to="/">Prisijungti</Link>
          </p>
        </section>
      ) : (
        <section>
          <p
            ref={errRef}
            className={errMsg ? "errMsg" : "offscreen"}
            aria-live="assertive"
          >
            {errMsg}
          </p>
          <h1>Registracija</h1>
         
          <Form onSubmit={handleSubmit}>
            {/******************* USERNAME ******************************************/}
            <Form.Group className="mb-3">
              <Form.Label htmlFor="username">
                Vartotojas:
                <FontAwesomeIcon
                  icon={faCheck}
                  className={validName ? "valid" : "hide"}
                />
                <FontAwesomeIcon
                  icon={faTimes}
                  className={validName || !userName ? "hide" : "invalid"}
                />
              </Form.Label>
              <Form.Control
                type="text"
                placeholder="Vartotojo vardas"
                id="username"
                ref={userRef}
                autoComplete="on"
                onChange={(e) => setUser(e.target.value)}
                required
                aria-invalid={validName ? "false" : "true"}
                aria-describedby="uidnote"
                onFocus={() => setUserFocus(true)}
                onBlur={() => setUserFocus(false)}
              />
            </Form.Group>
            <p
              id="uidnote"
              className={
                userFocus && userName && !validName
                  ? "instructions"
                  : "offscreen"
              }
            >
              <FontAwesomeIcon icon={faInfoCircle} />
              Nuo 4 iki 24 simbolių.
              <br />
              <FontAwesomeIcon icon={faInfoCircle} />
              Turi prasidėti raide. <br />
              <FontAwesomeIcon icon={faInfoCircle} />
              Leidžiami simboliai: raidės, skaitmenys, brūkšniai ir apatiniai brūkšneliai.
            </p>

            {/* ************* EMAIL ********************************* */}

            <Form.Group className="mb-3">
              <Form.Label htmlFor="email">
                El. paštas:
                <FontAwesomeIcon
                  icon={faCheck}
                  className={validEmail ? "valid" : "hide"}
                />
                <FontAwesomeIcon
                  icon={faTimes}
                  className={
                    validEmail || !email ? "hide" : "invalid"
                  }
                />
              </Form.Label>
              <Form.Control
                type="email"
                placeholder="El. paštas"
                id="email"
                ref={userRef}
                autoComplete="on"
                onChange={(e) => setEmail(e.target.value)}
                required
                aria-invalid={validEmail ? "false" : "true"}
                aria-describedby="uidnote"
                onFocus={() => setEmailFocus(true)}
                onBlur={() => setEmailFocus(false)}
              />
            </Form.Group>
            <p
              id="uidnote"
              className={
                emailFocus && email && !validEmail
                  ? "instructions"
                  : "offscreen"
              }
            >
              <FontAwesomeIcon icon={faInfoCircle} />
              Kol kas dar ne el. pašto formatas :)
              <br />
              {/* <FontAwesomeIcon icon={faInfoCircle} />
              Galimos tik raidės, įskaitant ir didžiąsias. <br /> */}
            </p>

            {/* ************* ROLE ********************************* */}

            {/* <Form.Group className="mb-3">
              <Form.Label htmlFor="role">
                Rolė:
                <FontAwesomeIcon
                  icon={faCheck}
                  className={validRole ? "valid" : "hide"}
                />
                <FontAwesomeIcon
                  icon={faTimes}
                  className={
                    validRole || !role ? "hide" : "invalid"
                  }
                />
              </Form.Label>
              <Form.Control
                type="text"
                placeholder="Rolė"
                id="role"
                ref={userRef}
                autoComplete="on"
                onChange={(e) => setRole(e.target.value)}
                required
                aria-invalid={validRole ? "false" : "true"}
                aria-describedby="uidnote"
                onFocus={() => setRoleFocus(true)}
                onBlur={() => setRoleFocus(false)}
              />
            </Form.Group>
            <p
              id="uidnote"
              className={
                roleFocus && role && !validRole
                  ? "instructions"
                  : "offscreen"
              }
            >
              <FontAwesomeIcon icon={faInfoCircle} />
             Rolę turi sudaryti nuo 4 iki 5 simbolių.
              <br />
              
            </p> */}

            {/* ************** PASSWORD ******************************** */}

            <Form.Group className="mb-3">
              <Form.Label htmlFor="password">
                Slaptažodis:
                <FontAwesomeIcon
                  icon={faCheck}
                  className={validPwd ? "valid" : "hide"}
                />
                <FontAwesomeIcon
                  icon={faTimes}
                  className={validPwd || !userPassword ? "hide" : "invalid"}
                />
              </Form.Label>
              <Form.Control
                type="password"
                placeholder="Slaptažodis"
                id="password"
                onChange={(e) => setPwd(e.target.value)}
                value={userPassword}
                required
                aria-invalid={validPwd ? "false" : "true"}
                aria-describedby="pwdnote"
                onFocus={() => setPwdFocus(true)}
                onBlur={() => setPwdFocus(false)}
              />
            </Form.Group>
            <p
              id="pwdnote"
              className={pwdFocus && !validPwd ? "instructions" : "offscreen"}
            >
              <FontAwesomeIcon icon={faInfoCircle} />
              Slaptažodį turi sudaryti nuo 8 iki 24 simbolių.
              <br />
              <FontAwesomeIcon icon={faInfoCircle} />
              Slaptažodį turi sudaryti didžiosios ir mažosios raidės, skaičius
              ir specialus simbolis. <br />
              <FontAwesomeIcon icon={faInfoCircle} />
              Galimi specialūs simboliai:{" "}
              <span aria-label="exclamation mark">!</span>{" "}
              <span aria-label="at symbol">@</span>{" "}
              <span aria-label="hashtag">#</span>{" "}
              <span aria-label="dollar sign">$</span>{" "}
              <span aria-label="percent">%</span>
            </p>

            {/* ************** CONFIRM PASSWORD ******************************** */}

            <Form.Group className="mb-3">
              <Form.Label htmlFor="confirm_pwd">
                Patvirtinti slaptažodį:
                <FontAwesomeIcon
                  icon={faCheck}
                  className={validMatch && matchPwd ? "valid" : "hide"}
                />
                <FontAwesomeIcon
                  icon={faTimes}
                  className={validMatch || !matchPwd ? "hide" : "invalid"}
                />
              </Form.Label>
              <Form.Control
                type="password"
                placeholder="Patvirtinti slaptažodį"
                id="confirm_pwd"
                onChange={(e) => setMatchPwd(e.target.value)}
                value={matchPwd}
                required
                aria-invalid={validMatch ? "false" : "true"}
                aria-describedby="confirmnote"
                onFocus={() => setMatchFocus(true)}
                onBlur={() => setMatchFocus(false)}
              />
            </Form.Group>
            <p
              id="confirmnote"
              className={matchFocus && !validMatch ? "instructions" : "offscreen"}
            >
              <FontAwesomeIcon icon={faInfoCircle} />
              Turi sutapti su aukščiau įvestu slpatažodžiu.
            </p>

        
            <Button variant="warning" type="submit" disabled={!validName || !validPwd || !validMatch ? true : false}>
              Registruotis
            </Button>
          </Form>

          <p>
            Jau užsiregistravęs?
            <br />
            <span className="line">
              <Link to="/login">Prisijungti</Link>
            </span>
          </p>
        </section>
      )}
    </div>
  );
}

export default Register