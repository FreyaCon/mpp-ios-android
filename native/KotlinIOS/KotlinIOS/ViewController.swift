import UIKit
import SharedCode

class ViewController: UIViewController {

    @IBOutlet private var label: UILabel!
    @IBOutlet private var button: UIButton!

    @IBAction func openURL(_ sender: UIButton ) {
        let URLstring: String = presenter.onButtonTapped(stationStart: "DHM", stationEnd:"KGX")
        UIApplication.shared.open(URL(string:URLstring)! as URL, options: [:], completionHandler:nil)
        
        
    }
    private let presenter: ApplicationContractPresenter = ApplicationPresenter()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.onViewTaken(view: self)
    }
}

extension ViewController: ApplicationContractView {
    func setLabel(text: String) {
        label.text = text
    }
}
