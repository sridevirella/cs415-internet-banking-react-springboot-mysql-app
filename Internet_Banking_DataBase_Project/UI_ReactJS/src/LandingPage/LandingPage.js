import React,{ Component } from "react";
import { css } from '@emotion/core';
import Button from '@material-ui/core/Button';
import BeatLoader from 'react-spinners/BeatLoader';
import "./landingPage.css";


const override = css`
    display: block;
    margin: 2 auto;
    border-color: white;
`;

class LandingPage extends Component {
  constructor(props) {
    super(props);
    // this.addActiveClass= this.addActiveClass.bind(this);
   
    this.state = {
        showSelectedTabDetails: {
          "id":"query1",
          "name":"Query 1",
          "className":"query-label",
          "shortDescription": "Please select a query",
        },
        queries:[
          {
            "id":"query1",
            "className":"query-label",
            "name":"FD Liquidate",
            "shortDescription": "Customers who has liquidated fixed deposits more than 3 times"
          },
          {
            "id":"query2",
            "className":"query-label",
            "name":"Loan Accounts",
            "shortDescription": "Customers who has only loan accounts with the loan amount"
          },
          {
            "id":"query3",
            "className":"query-label",
            "name":"Application Users",
            "shortDescription": "Number of customers who has used net banking application in each branch"
          },
          {
            "id":"query4",
            "className":"query-label",
            "name":"Bill Payment CC",
            "shortDescription": "Customers who has used only credit cards to pay bills online"
          },
          {
            "id":"query5",
            "className":"query-label",
            "name":"Transaction Amount",
            "shortDescription": "Total transaction amount in each branch"
          },
          {
            "id":"query6",
            "className":"query-label",
            "name":"Loan Payment",
            "shortDescription": "The number of customers who used only credit cards to pay loan amount"
          },
          {
            "id":"query7",
            "className":"query-label",
            "name":"Bill Payment SC",
            "shortDescription": "Number of bills which are paid through savings or checkin account"
          },
          {
            "id":"query8",
            "className":"query-label",
            "name":"Elite Customer",
            "shortDescription": "customers who has loan, fixed deposit, savings, check-in account and credit card"
          },
          {
            "id":"query9",
            "className":"query-label",
            "name":"Closed Accounts",
            "shortDescription": "Number of closed accounts in each branch"
          },
          {
            "id":"query10",
            "className":"query-label",
            "name":"Credit Limit",
            "shortDescription": "Credit limit of customers who has used only credit card to pay bills and loans"
          },
        ],
        parentHoldingQueryData:[],
        loading: ''
    };

    this.getApiData = this.getApiData.bind(this);
    this.selectedTab = this.selectedTab.bind(this);
  }
  
  selectedTab(e, item) {
    console.log(`selected ${JSON.stringify(item)}`);
    let selectedTabDetails = this.state.queries;
    selectedTabDetails.forEach(function (obj) {
      if(obj.id === item.id) {
        obj.className = "query-label active";
      }else{
        obj.className = "query-label";
      }
      })
    this.setState(prevState => ({
      showSelectedTabDetails: {                   // object that we want to update
          ...prevState.showSelectedTabDetails,    // keep all other key-value pairs
          name: item.name,
          id: item.id,
          shortDescription: item.shortDescription       // update the value of specific key
      },
      queries:selectedTabDetails
      
  }))
  };
  
  getApiData(e, id) {    
    this.setState({loading: true});
    // make asynchronous call
    console.log(`about to query the ${id} statement.`);
    fetch(`http://localhost:8080/query?id=${id}`) // take the ID and fetch the respective url
    .then(res => res.json())
    .then((data) => {
      setTimeout(() => {
        this.setState({loading: false});
        this.props.sendData(data);
      }, 500);
      
    })
    .catch(console.log);
    
  }

  sendDataToAppComponent = (landingPageData) => {
    this.setState({ parentHoldingQueryData: landingPageData });
  }

  render() {    
    
    return (

        <div className="short-description">
          {/* Query's tabs here */}
          {
            this.queriesLabels = this.state.queries.map((item, index) =>
              <label 
                className={item.className} 
                onClick={((e) => this.selectedTab(e, item))}
                key={item.id}> 
                {item.name}                
              </label>
            )
          } 
          {/* selected query then assosiated short description */}
          { this.state.showSelectedTabDetails &&
          <p className="description-text">
            {this.state.showSelectedTabDetails.shortDescription}
            <Button 
              variant="contained" 
              color="primary"
              size="large"              
              onClick={((e) => this.getApiData(e, this.state.showSelectedTabDetails.id))} 
              >
              <BeatLoader
                css={override}
                sizeUnit={"px"}
                size={15}
                color={'#FFFFFF'}
                loading={this.state.loading}
              />
              {this.state.loading ? '' : 'Search'}           
            </Button>           
          </p>
          }       
        </div>
    )
  }
}

export default LandingPage;