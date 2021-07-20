import UIKit
import SharedCode

class ViewController: UIViewController {
    
    @IBOutlet private var label: UILabel!
    @IBOutlet private var button: UIButton!
    @IBOutlet private var startStation: UIPickerView!
    @IBOutlet private var endStation: UIPickerView!
    @IBOutlet private var table: UITableView!
    private let presenter: ApplicationContractPresenter = ApplicationPresenter()
    private var stationData: [Station]!
    private var station1: Station!
    private var station2: Station!
    private var journeys: [Train]!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.onViewTaken(view: self)
        self.startStation.delegate = self
        self.startStation.dataSource = self
        self.endStation.delegate = self
        self.endStation.dataSource = self
        setStations()
        station1 = stationData[0]
        station2 = stationData[0]
    }
    
//response to pressing button
    @IBAction func openURL(_ sender: UIButton ) {
        if(presenter.checkForSameStations(station1: station1, station2: station2)) {
           toast(text:"Start and End Stations should be different")
        } else {
        presenter.onButtonTapped(stationStart:station1, stationEnd: station2)
        }
    }
    
}

extension ViewController: ApplicationContractView { //Functions required in the contract
    func displayJourneys(trains: TrainData) {
        self.journeys = trains.outboundJourneys
        table.dataSource = self
        table.reloadData()
        table.rowHeight = UITableView.automaticDimension
        self.table.register(UITableViewCell.classForKeyedArchiver(), forCellReuseIdentifier: "tableCell")
    }
    
    func openLink(linkString:String) {
        UIApplication.shared.open(URL(string:linkString)! as URL, options: [:], completionHandler:nil)
    }
    
    func setStations() {
        stationData = presenter.stations
    }
    
    func toast(text:String) {
        let alert = UIAlertController(title: "", message: text, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
        self.present(alert, animated: true, completion: nil)
    }
    
    func trainVisibility(bool: Bool) {
    }
}

extension ViewController: UIPickerViewDelegate {//functions set the spinner options and retrieve current selections
    func pickerView(_ picker: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return stationData[row].name
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if (pickerView==startStation) {
            station1 = stationData[row]
        }
        if (pickerView==endStation) {
            station2 = stationData[row]
        }
    }
}

extension ViewController: UIPickerViewDataSource {//functions required by UIPickerViewDataSource to set up spinners
    func numberOfComponents(in picker: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ picker: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return stationData.count
    }
}
    
extension ViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let journey = journeys[indexPath.item]
        let cell = tableView.dequeueReusableCell(withIdentifier: "trainCell", for: indexPath) as! TableCell
        cell.station1.text = journey.startStation.code
        cell.station2.text = journey.endStation.code
        cell.departureTime.text = timeToString(time:journey.startTime)
        cell.arrivalTime.text = timeToString(time:journey.endTime)
        cell.status.text = journey.status
        return cell
    }
    
    func tableView(_ table: UITableView, numberOfRowsInSection section: Int) -> Int {
        return journeys.count
    }
    
    func timeToString(time:SimpleDateTime) -> String {
    if(time.minute<10) {
        return String(time.hour)+":0"+String(time.minute)
    }
    return String(time.hour)+":"+String(time.minute)
    }
}

class TableCell : UITableViewCell {
    @IBOutlet weak var station1: UILabel!
    @IBOutlet weak var station2: UILabel!
    @IBOutlet weak var departureTime: UILabel!
    @IBOutlet weak var arrivalTime: UILabel!
    @IBOutlet weak var status: UILabel!
}


