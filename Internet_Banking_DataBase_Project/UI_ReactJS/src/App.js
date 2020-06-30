import React,{ Component } from "react";
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import LandingPage from  "./LandingPage/LandingPage.js";
import Queries from './queries/queries.js';
import collegeLogo from "./college-logo.jpeg";
import WelcomePage from "./WelcomePage.js"
class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      parentHoldingQueryData: null,
      childData:null,
      welcomePage:true
    };
  }

  getData = (data) => {
    this.setState({childData: data});     
  }

  isWelcomePageShown= (responseFromWelcomePage) => {
    this.setState({welcomePage: responseFromWelcomePage})
  }
  
  render() {
    return (
      <div className="App">
        <header className="App-header">     
          <img src={collegeLogo} alt="college-logo"/>               
        </header>
        { this.state.welcomePage ? 
        <WelcomePage isWelcomePage={this.isWelcomePageShown}/> 
        : 
        <div>
          <br /><br/><br /><br/>
          <LandingPage sendData={this.getData} />
          <Queries dataFromLandingPage = {this.state.childData} /> 
        </div>
        }        
      </div>
    );
  }
}

export default App;
