import React,{ Component } from "react";
import girl from  "./girl.jpg";

class WelcomePage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      showWelcomePage:true
    }
  }
  isWelcomePageShown = () => {
    // send the data to parent
    this.props.isWelcomePage(!this.state.showWelcomePage);
  }

  render(){
    return(
      <div className="welcome-page">
        <img src={girl} alt="girl picture" className="girl-picture" />
        <div className="proceed-text">
          <h2>WELCOME</h2>
          <p>Let's get started with quering </p>          
          <button 
            className="proceed-button"
            onClick={((e) => this.isWelcomePageShown())}          
          >Proceed</button>
        </div>
      </div>
    )
  }
}

export default WelcomePage;