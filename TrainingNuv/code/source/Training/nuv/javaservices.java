package Training.nuv;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.softwareag.util.IDataMap;
// --- <<IS-END-IMPORTS>> ---

public final class javaservices

{
	// ---( internal utility methods )---

	final static javaservices _instance = new javaservices();

	static javaservices _newInstance() { return new javaservices(); }

	static javaservices _cast(Object o) { return (javaservices)o; }

	// ---( server methods )---




	public static final void formatPerusahaan (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(formatPerusahaan)>> ---
		// @sigtype java 3.5
		// [i] field:0:required namaPerusahaan
		// [i] field:0:required statusPerusahaan
		// [o] field:0:required namaPerusahaan
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	namaPerusahaan = IDataUtil.getString( pipelineCursor, "namaPerusahaan" );
			String	statusPerusahaan = IDataUtil.getString( pipelineCursor, "statusPerusahaan" );
		pipelineCursor.destroy();
		
		
		if (namaPerusahaan.startsWith("PT.") && "Terbuka".equals(statusPerusahaan)) { 
			namaPerusahaan = namaPerusahaan.toUpperCase().concat(" Tbk."); 
			} 
		
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "namaPerusahaan", namaPerusahaan );
		pipelineCursor_1.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void formatPerusahaanTraining (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(formatPerusahaanTraining)>> ---
		// @sigtype java 3.5
		// [i] field:0:required namaPerusahaan
		// [i] field:0:required statusPerusahaan
		// [o] field:0:required namaCompany
		IDataMap idm = new IDataMap(pipeline);
		//		get input value
		String	namaPerusahaan =idm.getAsNonEmptyString("namaPerusahaan");
		String	statusPerusahaan = idm.getAsNonEmptyString("statusPerusahaan" );
		if (namaPerusahaan.startsWith("PT.") && "Terbuka".equals(statusPerusahaan)) { 
			namaPerusahaan = namaPerusahaan.toUpperCase().concat(" Tbk  banyakk."); 
			} 
		//		define output value
		idm.put("namaCompany", namaPerusahaan);
		// --- <<IS-END>> ---

                
	}



	public static final void trainReadArray (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(trainReadArray)>> ---
		// @sigtype java 3.5
		// [i] record:0:required demoDoc
		// [i] - field:0:required PONumber
		// [i] - field:0:required SenderID
		// [i] - record:1:required Items
		// [i] -- field:0:required Description
		// [i] -- field:0:required Quantity
		// [o] record:0:required outDemoDoc
		// [o] - field:0:required PONumber
		// [o] - record:1:required outItems
		// [o] -- field:0:required Description
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		
			// demoDoc
		String PONumber = "";
		String Description = "";
		IData demoDoc = IDataUtil.getIData(pipelineCursor, "demoDoc");
		IData[] outItems = null;
			if ( demoDoc != null)
			{
				IDataCursor demoDocCursor = demoDoc.getCursor();
					PONumber = IDataUtil.getString( demoDocCursor, "PONumber" );
					String	SenderID = IDataUtil.getString( demoDocCursor, "SenderID" );
		
					// i.Items
					IData[]	Items = IDataUtil.getIDataArray( demoDocCursor, "Items" );
					if ( Items != null)
					{
						 outItems = new IData[Items.length];
						for ( int i = 0; i < Items.length; i++ )
						{
							IDataCursor ItemsCursor = Items[i].getCursor();
		
			                // Buat elemen baru untuk setiap item
			                outItems[i] = IDataFactory.create();
			                IDataCursor outItemsCursor = outItems[i].getCursor();
		
			                Description = IDataUtil.getString(ItemsCursor, "Description");
			                String Quantity = IDataUtil.getString(ItemsCursor, "Quantity");
		
			                // Tambahkan data ke elemen baru
			                IDataUtil.put(outItemsCursor, "Description", Description);
			                IDataUtil.put(outItemsCursor, "Quantity", Quantity);
		
			                outItemsCursor.destroy();
			                ItemsCursor.destroy();
						}
					}
				demoDocCursor.destroy();
			}
		pipelineCursor.destroy();
		
		// pipeline
		
		// pipeline
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		
		// outDemoDoc
		IData outDemoDoc = IDataFactory.create();
		IDataCursor outDemoDocCursor = outDemoDoc.getCursor();
		IDataUtil.put(outDemoDocCursor, "PONumber", PONumber);
		
		if (outItems != null) {
		    IDataUtil.put(outDemoDocCursor, "outItems", outItems);
		}
		outDemoDocCursor.destroy();
		
		IDataUtil.put(pipelineCursor_1, "outDemoDoc", outDemoDoc);
		pipelineCursor_1.destroy();
		// --- <<IS-END>> ---

                
	}
}

