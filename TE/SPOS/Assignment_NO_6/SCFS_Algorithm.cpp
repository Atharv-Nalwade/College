#include<iostream>
using namespace std;

int main(){
        int n;                                                                                                  // no. of processes
	cout<<"enter number of processes";
	cin>>n;

	int processes_burst_time[n];
	// int arrival_time;

	// cout<<"Enter arrival time for process 1";
	// cin>>arrival_time;

 	for(int i=0;i<n;i++){
     	cout<<"Enter Burst time for "<<i+1<<"th processes";
     	cin>>processes_burst_time[i];
 	}

 	int waiting_time[n];
 	waiting_time[0]=0;

 	for(int i=1;i<n;i++) waiting_time[i]=processes_burst_time[i-1]+waiting_time[i-1];

    

 	int turn_around_time[n];
 	for(int i=0;i<n;i++) turn_around_time[i]=processes_burst_time[i]+waiting_time[i];
	 
    

 	//dispalying it
 	for(int i=0;i<n;i++){
     	cout<<"For "<<i+1<<" th process Waiting time is "<<waiting_time[i]<<" And turn around time is "<<turn_around_time[i]<<endl;
 	}
	 
  	float average_TAT;
 	for(int i=0;i<n;i++) average_TAT+=turn_around_time[i];
 	cout<<"Average Turn around  time for given set of processses is  "<<average_TAT/n<<endl;

  	float average_WT=0;
 	for(int i=0;i<n;i++) average_WT+=waiting_time[i];
 	cout<<"Average waiting time for given set of processses is  "<<average_WT/n;
}