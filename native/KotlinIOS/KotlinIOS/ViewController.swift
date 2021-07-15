import UIKit
import SharedCode

class ViewController: UIViewController {
    
    @IBOutlet private var label: UILabel!
    @IBOutlet private var button: UIButton!
    @IBOutlet private var startStation: UIPickerView!
    @IBOutlet private var endStation: UIPickerView!
    private var station1: Station = Station(stationName: "",stationCode: "")
    private var station2: Station = Station(stationName: "",stationCode: "")
    private let presenter: ApplicationContractPresenter = ApplicationPresenter()
    private var stationData: [Station] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.onViewTaken(view: self)
        self.startStation.delegate = self
        self.startStation.dataSource = self
        self.endStation.delegate = self
        self.endStation.dataSource = self
        setStations()
    }
    

    @IBAction func openURL(_ sender: UIButton ) {
        presenter.onButtonTapped(stationStart:station1, stationEnd: station2)
    }
    
}

extension ViewController: ApplicationContractView {
    func setLabel(text: String) {
        label.text = text
    }
    
    func openLink(linkString:String) {
        UIApplication.shared.open(URL(string:linkString)! as URL, options: [:], completionHandler:nil)
    }
    
    func setStations() {
        stationData = presenter.stations
    }
}

extension ViewController: UIPickerViewDelegate {
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

extension ViewController: UIPickerViewDataSource {
    func numberOfComponents(in picker: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ picker: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return stationData.count
    }

}
