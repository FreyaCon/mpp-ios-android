import UIKit
import SharedCode

class ViewController: UIViewController {
    
    @IBOutlet private var label: UILabel!
    @IBOutlet private var button: UIButton!
    @IBOutlet private var startStation: UIPickerView!
    @IBOutlet private var endStation: UIPickerView!
    private var station1: String = ""
    private var station2: String = ""
    private let presenter: ApplicationContractPresenter = ApplicationPresenter()
    private let stationData: [String] = ["Kings Cross", "Euston", "Durham", "York", "Birmingham New Street"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.onViewTaken(view: self)
        self.startStation.delegate = self
        self.startStation.dataSource = self
        self.endStation.delegate = self
        self.endStation.dataSource = self
    }
    

    @IBAction func openURL(_ sender: UIButton ) {
        let URLstring: String = presenter.onButtonTapped(stationStart: presenter.convertToCode(stationName: station1), stationEnd: presenter.convertToCode(stationName: station2))
        UIApplication.shared.open(URL(string:URLstring)! as URL, options: [:], completionHandler:nil)
        
    }
    
}

extension ViewController: ApplicationContractView {
    func setLabel(text: String) {
        label.text = text
    }
}

extension ViewController: UIPickerViewDelegate {
    func pickerView(_ picker: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return stationData[row]
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
